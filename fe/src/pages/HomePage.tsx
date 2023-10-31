import { styled } from 'styled-components';
import { MainFeedItem } from 'components/feedMain/FeedMain';

export const HomePage = () => {
  return (
    <Wrapper>
      <MainFeedItem feed={MOCK} />
      <MainFeedItem feed={MOCK} />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 16px;
  padding: 32px 0;
  align-items: center;
`;

const MOCK = {
  id: '2',
  member: {
    id: '2',
    imageUrl: 'www.google.com/',
    nickname: '박콩불2',
    tasteMood: { id: '1', name: '베지테리안' },
  },
  createdAt: '2023-10-17T16:54:03',
  updatedAt: '2023-10-18T11:54:03',
  location: '맛있게 매운 콩볼 범계점',
  review:
    '맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.맛있게 먹음.',
  storeMood: [
    { id: '1', name: '따뜻함' },
    { id: '2', name: '가족과함께' },
    { id: '3', name: '가게무드' },
  ],
  images: [
    {
      id: '1',
      imageUrl:
        'https://img.daily.co.kr/@files/www.daily.co.kr/content/food/2020/20200730/40d0fb3794229958bdd1e36520a4440f.jpg',
      menu: {
        name: '마라탕',
        rating: 4,
      },
    },
    {
      id: '2',
      imageUrl:
        'https://img.daily.co.kr/@files/www.daily.co.kr/content/food/2020/20200730/40d0fb3794229958bdd1e36520a4440f.jpg',
      menu: {
        name: '감자탕',
        rating: 3,
      },
    },
  ],
  likeCount: 17,
  isLiked: true,
  commentCount: 3,
};
