type ProfileMemberInfo = {
  id: string;
  profileImageId: string;
  profileImageUrl: string;
  nickname: string;
  email: string;
  tasteMoodId: string;
  followingCount: number;
  followerCount: number;
  following: boolean;
  followed: boolean;
  feedCount: number;
};

type MyFeeds = {
  id: string;
  imageUrl: string;
};

type ProfileImageBody = {
  profileImageId: string;
  tasteMoodId: null;
  nickname: null;
};

type ProfileEditBody = {
  // 이전값이랑 비교해서 | null 해도 되는지 보기
  profileImageId: string | null;
  tasteMoodId: string | null;
  nickname: string | null;
};
