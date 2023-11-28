import { useQuery } from '@tanstack/react-query';
import { getProfile } from 'service/axios/profile/profile';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetProfile = (id?: string) => {
  return useQuery({
    queryKey: [QUERY_KEY.profile, id],
    queryFn: async () => {
      const { myFeeds, ...memberProfileData } = await getProfile(id);
      return { myFeeds, memberProfileData };
    },
    staleTime: Infinity,
  });
};
