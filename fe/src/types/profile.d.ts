type ProfileMemberInfo = {
  memberId: string;
  imageUrl: string;
  nickname: string;
  email: string;
  tasteMood: string;
  myFeeds: MyFeeds[];
  // myFeedsCount: number;
};

type MyFeeds = {
  id: string;
  imageUrl: string;
};
