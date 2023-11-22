import { useQuery } from '@tanstack/react-query';
import { getProfile } from 'service/axios/profile/profile';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetProfile = (id?: string) =>
  useQuery({
    queryKey: [QUERY_KEY.profile, id],
    queryFn: () => getProfile(id),
    staleTime: Infinity,
  });
