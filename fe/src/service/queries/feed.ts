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
// import { usePageNavigator } from 'hooks/usePageNavigator';
import { PATH } from 'constants/path';

export const useAllFeeds = () => {
  const query = useInfiniteQuery({
    queryKey: [QUERY_KEY.allFeeds],
    queryFn: ({ pageParam = 0 }) => getAllFeeds(pageParam),
    getNextPageParam: (lastPage) => {
      return lastPage.last ? undefined : lastPage.number + 1;
    },
  });

  const feeds = query.data?.pages?.reduce((acc, page) => {
    return [...acc, ...page.content];
  }, []);

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
  // const { navigateToHome } = usePageNavigator();
  const queryClient = useQueryClient();
  const navigate = useNavigate();
  const toast = useToast();

  return useMutation({
    mutationFn: (body: NewFeedBody) =>
      id ? putEditFeed(id, body) : postNewFeed(body),
    onSuccess: () => {
      // navigateToHome(); // 수정 예정(post시에는 home으로, put시에는 detail로)
      if (id) {
        // edit 성공시 detail로 이동
        queryClient.invalidateQueries([QUERY_KEY.feedDetail, id]); // invalidate 꼭해야하는지 확인하기
        navigate(`${PATH.DETAIL_FEED}/${id}`, { replace: true });
      } else {
        // post 성공시 home으로 이동
        queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
        navigate(PATH.HOME, { replace: true });
      }
    },
    onError: (error: AxiosError<ErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log('feed editor error: ', error);

      errorData && toast.error(errorData.message);
    },
  });
};

export const useDeleteFeed = () => {
  // const { navigateToHome } = usePageNavigator();
  const queryClient = useQueryClient();
  const navigate = useNavigate();
  const toast = useToast();

  return useMutation({
    mutationFn: (id: string) => deleteFeed(id),
    onSuccess: () => {
      // navigateToHome();
      queryClient.invalidateQueries([QUERY_KEY.allFeeds]);
      navigate(PATH.HOME, { replace: true });
    },

    onError: (error: AxiosError<ErrorResponse>) => {
      const errorData = error?.response?.data;
      console.log('delete feed error: ', error);

      errorData && toast.error(errorData.message);
    },
  });
};
