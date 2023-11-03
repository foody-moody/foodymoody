import { useQuery } from '@tanstack/react-query';
import { getProfile } from 'api/profile/profile';
import { QUERY_KEY } from 'constants/queryKey';

export const useGetProfile = (id?: string) =>
  useQuery({
    queryKey: [QUERY_KEY.profile, id],
    queryFn: () => getProfile(id),
  });
