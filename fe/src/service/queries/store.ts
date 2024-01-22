import { useQuery } from '@tanstack/react-query';
import { getSearchStores, getStoreDetail } from 'service/axios/store/store';
import { QUERY_KEY } from 'service/constants/queryKey';

export const useGetStores = (keyword: string) =>
  useQuery({
    queryKey: [QUERY_KEY.stores, keyword],
    queryFn: () => getSearchStores(keyword),
    enabled: !!keyword,
  });

export const useGetStoreDetail = (storeId: string) =>
  useQuery<StoreDetail>({
    queryKey: [QUERY_KEY.stores, storeId],
    queryFn: () => getStoreDetail(storeId),
    staleTime: Infinity,
    enabled: !!storeId,
  });
