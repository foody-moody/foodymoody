import { privateApi } from 'service/axios/fetcher';
import { END_POINT } from 'service/constants/endpoint';

export const getProfile = async (memberId?: string) => {
  const { data } = await privateApi.get(END_POINT.member(memberId));
  return data;
};

export const patchProfileImage = async (
  memberId: string,
  body: ProfileImageBody
) => {
  const { data } = await privateApi.patch(END_POINT.member(memberId), body);
  return data;
};

export const getNicknameDuplicate = async (nickname: string) => {
  const encodedNickname = encodeURIComponent(nickname);
  const { data } = await privateApi.get(END_POINT.nickName(encodedNickname));
  return data;
};

export const patchEditProfile = async (
  memberId: string,
  body: ProfileEditBody
) => {
  const { data } = await privateApi.patch(END_POINT.member(memberId), body);
  return data;
};
