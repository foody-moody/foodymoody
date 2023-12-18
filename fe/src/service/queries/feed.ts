import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useNavigate } from 'react-router-dom';
import { useToast } from 'recoil/toast/useToast';
import {
  deleteFeed,
  getAllFeeds,
  getFeedDetail,
  postNewFeed,
  putEditFeed,
} from 'service/axios/feed/feed';
import { QUERY_KEY } from 'service/constants/queryKey';
import { PATH } from 'constants/path';

export const useAllFeeds = () => {
  const query = useInfiniteQuery({
    queryKey: [QUERY_KEY.allFeeds],
    queryFn: ({ pageParam = 0 }) => getAllFeeds(pageParam),
    getNextPageParam: (lastPage) => {
      return lastPage.last ? undefined : lastPage.number + 1;
    },
    suspense: true,
  });

  const feeds = query.data?.pages?.flatMap((page) => page.content) || [];

  console.log(feeds);

  return {
    ...query,
    feeds,
  };
};

export const useFeedDetail = (id: string) =>
  useQuery({
    queryKey: [QUERY_KEY.feedDetail, id],
    queryFn: () => getFeedDetail(id),
    enabled: !!id,
  });

export const useFeedEditor = (id?: string) => {
  const queryClient = useQueryClient();
  const navigate = useNavigate();
  const toast = useToast();

  return useMutation({
    mutationFn: (body: NewFeedBody) =>
      id ? putEditFeed(id, body) : postNewFeed(body),
    onSuccess: () => {
      if (id) {
        // edit 성공시 detail로 이동
        queryClient.invalidateQueries([QUERY_KEY.feedDetail, id]);
        queryClient.invalidateQueries([QUERY_KEY.allFeeds]);

        toast.success('피드를 수정했습니다.');
        navigate(`${PATH.DETAIL_FEED}/${id}`, {
          state: { background: 'detailFeed' },
          replace: true,
        });
      } else {
        // post 성공시 home으로 이동
        queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
        toast.success('피드를 등록했습니다.');
        navigate(PATH.HOME, { replace: true });
      }
    },
    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log('feed editor error: ', error);

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteFeed = () => {
  const queryClient = useQueryClient();
  const navigate = useNavigate();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => deleteFeed(id),
    onSuccess: () => {
      queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
      toast.success('피드가 삭제되었습니다.');
      navigate(PATH.HOME, { replace: true });
    },

    onError: (error: AxiosError<CustomErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log('delete feed error: ', error);

      errorData && toast.error(errorData.message);
    },
  });
};
