import { useQuery } from '@tanstack/react-query';
import { getTasteMoods } from 'service/axios/mood/mood';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetTasteMood = () =>
  // 반환타입 설정하기
  useQuery({
    queryKey: [QUERY_KEY.tasteMood],
    queryFn: () => getTasteMoods(),
    staleTime: Infinity,
  });
