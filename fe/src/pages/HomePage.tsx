import { useLocation } from 'react-router-dom';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { MainFeedItem } from 'components/feedMain/FeedMainItem';
import { DetailFeedModalPage } from './DetailFeedPage';
import { NewFeedModalPage } from './NewFeedPage';

export const HomePage = () => {
  const location = useLocation();
  const background = location.state && location.state.background;

  return (
    <Wrapper>
      {MOCK.map((feed) => (
        <MainFeedItem feed={feed} key={feed.id} />
      ))}

      {background === 'detailFeed' && <DetailFeedModalPage />}
      {background === 'newFeed' && <NewFeedModalPage />}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  position: relative;
  flex-direction: column;
  gap: 16px;
  padding: 32px 0;
  align-items: center;

  ${media.xs} {
    padding-bottom: 59px;
  }
`;

const MOCK = [
  {
    id: '1',
    member: {
      id: '1',
      imageUrl: 'www.google.com/',
      nickname: '1번 회원님',
      tasteMood: { id: '2', name: '달달인' },
    },
    createdAt: '2023-10-17T16:54:03',
    updatedAt: '2023-10-18T11:54:03',
    location: '맘스터치 범계점',
    review: '햄붜거는 맛있어',
    storeMood: [
      { id: '5', name: '가게무드1' },
      { id: '6', name: '2가게무드' },
    ],
    images: [
      {
        id: '6',
        imageUrl:
          'https://img.daily.co.kr/@files/www.daily.co.kr/content/food/2020/20200730/40d0fb3794229958bdd1e36520a4440f.jpg',
        menu: {
          name: '마라탕',
          rating: 4,
        },
      },
      {
        id: '21241',
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
  },
  {
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
  },
];
