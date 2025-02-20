import {
  useInfiniteQuery,
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import { AxiosError } from 'axios';
import { useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { useToggle } from 'recoil/booleanState/useToggle';
import { useToast } from 'recoil/toast/useToast';
import {
  deleteFeed,
  getAllFeeds,
  getAllProfileFeeds,
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

  // console.log(feeds);

  return {
    ...query,
    feeds,
  };
};

export const useAllProfileFeeds = (memberId?: string) => {
  const query = useInfiniteQuery({
    queryKey: [QUERY_KEY.profileFeeds, memberId],
    queryFn: ({ pageParam = 0 }) => getAllProfileFeeds(pageParam, 10, memberId),
    getNextPageParam: (lastPage) => {
      return lastPage.last ? undefined : lastPage.number + 1;
    },
    // suspense: true,
  });

  const profileFeeds = useMemo(() => {
    return query.data?.pages.flatMap((page) => page.content) ?? [];
  }, [query.data]);

  return {
    ...query,
    profileFeeds,
  };
};

export const useFeedDetail = (id: string) =>
  useQuery<MainFeed>({
    queryKey: [QUERY_KEY.feedDetail, id],
    queryFn: () => getFeedDetail(id),
    enabled: !!id,
    // suspense: true,
  });

export const useFeedEditor = (id?: string) => {
  const queryClient = useQueryClient();
  const search = useToggle('search');
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
      search.toggleOn();
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
