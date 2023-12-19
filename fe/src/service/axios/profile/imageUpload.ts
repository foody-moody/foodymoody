import { END_POINT } from 'service/constants/endpoint';
import { multiFormApi } from '../fetcher';

export const postUserImage = async (file: FormData) => {
  const { data } = await multiFormApi.post(END_POINT.imageUpload('user'), file);
  return data;
};
