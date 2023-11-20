import { useQuery } from '@tanstack/react-query';
import { getTasteMoods } from 'api/mood/mood';
import { QUERY_KEY } from 'constants/queryKey';

export const useGetTasteMood = () =>
  useQuery({
    queryKey: [QUERY_KEY.tasteMood],
    queryFn: () => getTasteMoods(),
    staleTime: Infinity,
  });
