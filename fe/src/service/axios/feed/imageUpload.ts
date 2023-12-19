import { END_POINT } from 'service/constants/endpoint';
import { multiFormApi } from '../fetcher';

export const postFeedImage = async (file: FormData) => {
  const { data } = await multiFormApi.post(END_POINT.imageUpload('feed'), file);
  return data;
};
