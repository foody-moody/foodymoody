import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { StoreMoodBadge } from 'components/common/badge/StoreMoodBadge';
import {
  DotGhostIcon,
  HeartSmallEmpty,
  ShareIcon,
  StoreIcon,
  HeartBgIcon,
  ChatDotsIcon,
} from 'components/common/icon/icons';
import { UserImage } from 'components/common/userImage/UserImage';
import { FollowListButton } from 'components/follow/followButton/FollowListButton';

export const CollectionDetailPage = () => {
  const collection = {
    id: 'fd9ecac46496ef07ec38ccbb',
    author: {
      id: 'fd9ec99e6496ef07ec38cc96',
      name: '아티',
      mood: 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1',
      profileImageUrl:
        'https://cdn.pixabay.com/photo/2020/05/17/20/21/cat-5183427_1280.jpg',
    },
    thumnailImgUrl:
      'https://d2v80xjmx68n4w.cloudfront.net/gigs/fPoZ31584321311.jpg',
    title: '테스트 컬렉션',
    description:
      '이보다 맛있는 맛집 컬렉션 이세상에 없습니다.이보다 맛있는 맛집 컬렉션 이세상에 없습니다.이보다 맛있는 맛집 컬렉션 이세상에 없습니다.이보다 맛있는 맛집 컬렉션 이세상에 없습니다.이보다 맛있는 맛집 컬렉션 이세상에 없습니다.이보다 맛있는 맛집 컬렉션 이세상에 없습니다.',
    likeCount: 0,
    followerCount: 0,
    viewCount: 0,
    feedCount: 5,
    commentCount: 3,
    moods: [
      {
        id: 'fd9eca8d6496ef07ec38ccb8',
        name: '행복',
      },
      {
        id: 'fd9eca9e6496ef07ec38ccb9',
        name: '행복',
      },
      {
        id: 'fd9ecaaf6496ef07ec38ccba',
        name: '행복',
      },
    ],
    createdAt: '2024-01-12T12:21:31.460596',
    updatedAt: '2024-01-12T12:21:31.498458',
    liked: false,

    private: false,

    feeds: [
      {
        id: '20adfddfb74c89083d7d454c',
        thumbnailUrl:
          'https://d2v80xjmx68n4w.cloudfront.net/gigs/fPoZ31584321311.jpg',
        content: '맛있어요!',
        storeMood: [
          {
            id: '1',
            name: '가족과 함께',
          },
          {
            id: '3',
            name: '감성',
          },
          {
            id: '4',
            name: '데이트',
          },
        ],
        likeCount: 0,
        commentCount: 0,
        createdAt: '2024-01-19T07:44:50.143663',
        updatedAt: null,
        liked: false,
      },
      {
        id: '20adfe00b74c89083d7d4552',
        thumbnailUrl:
          'https://d2v80xjmx68n4w.cloudfront.net/gigs/fPoZ31584321311.jpg',
        content: '맛있어요!',
        storeMood: [
          {
            id: '1',
            name: '가족과 함께',
          },
          {
            id: '3',
            name: '감성',
          },
          {
            id: '4',
            name: '데이트',
          },
        ],
        likeCount: 0,
        commentCount: 0,
        createdAt: '2024-01-19T07:44:50.17695',
        updatedAt: null,
        liked: false,
      },
      {
        id: '20adfe22b74c89083d7d4558',
        thumbnailUrl:
          'https://d2v80xjmx68n4w.cloudfront.net/gigs/fPoZ31584321311.jpg',
        content: '맛있어요!',
        storeMood: [
          {
            id: '1',
            name: '가족과 함께',
          },
          {
            id: '3',
            name: '감성',
          },
          {
            id: '4',
            name: '데이트',
          },
        ],
        likeCount: 0,
        commentCount: 0,
        createdAt: '2024-01-19T07:44:50.21004',
        updatedAt: null,
        liked: false,
      },
      {
        id: '20adfe3eb74c89083d7d455e',
        thumbnailUrl:
          'https://d2v80xjmx68n4w.cloudfront.net/gigs/fPoZ31584321311.jpg',
        content: '맛있어요!',
        storeMood: [
          {
            id: '1',
            name: '가족과 함께',
          },
          {
            id: '3',
            name: '감성',
          },
          {
            id: '4',
            name: '데이트',
          },
        ],
        likeCount: 0,
        commentCount: 0,
        createdAt: '2024-01-19T07:44:50.238608',
        updatedAt: null,
        liked: false,
      },
      {
        id: '20adfe5db74c89083d7d4564',
        thumbnailUrl:
          'https://d2v80xjmx68n4w.cloudfront.net/gigs/fPoZ31584321311.jpg',
        content: '맛있어요!',
        storeMood: [
          {
            id: '1',
            name: '가족과 함께',
          },
          {
            id: '3',
            name: '감성',
          },
          {
            id: '4',
            name: '데이트',
          },
        ],
        likeCount: 0,
        commentCount: 0,
        createdAt: '2024-01-19T07:44:50.269128',
        updatedAt: null,
        liked: false,
      },
    ],
  };

  return (
    <Wrapper>
      <HeroImage>
        <MainThumnail src={collection.thumnailImgUrl} />
        <Author>
          <UserImage imageUrl={collection.author.profileImageUrl} />
          <UserName>{collection.author.name}</UserName>
          <FollowListButton
            memberId={collection.author.id}
            isFollowing={false}
            size="xs"
            width={80}
          />
        </Author>
      </HeroImage>

      <InfoWrapper>
        <Info>
          <div>
            <Title>
              <h1>{collection.title}</h1>
              <span>날짜 및 업데이트 관련</span>
            </Title>
            <p>{collection.description}</p>
          </div>
          <div>
            <DotGhostIcon /> {/* TODO. 이거 나중에 수정 */}
          </div>
        </Info>

        <Moods>
          {collection.moods.map((mood) => (
            <StoreMoodBadge name={mood.name} />
          ))}
        </Moods>

        <ActionBar>
          <button>
            <HeartSmallEmpty />
            <p>좋아요</p>
          </button>
          {/* <button>댓글</button> */}
          <button>
            <ShareIcon />
            <p>공유하기</p>
          </button>
        </ActionBar>
      </InfoWrapper>

      <FeedsWrapper>
        <h2>
          피드들 <span>15</span>
        </h2>

        <ul>
          {collection.feeds.map((feed) => (
            <FeedItem>
              <img src={feed.thumbnailUrl} alt="" />
              <FeedInfo>
                <FeedTop>
                  <FeedHeader>
                    <FeedTitle>
                      <StoreIcon /> <h3>가게 이름이 올거에요~~</h3>
                    </FeedTitle>
                    <DotGhostIcon /> {/* TODO. 이거 나중에 수정 */}
                  </FeedHeader>
                  <p>{feed.content}</p>
                </FeedTop>
                <FeedBottom>
                  <FeedMoods>
                    {feed.storeMood.map((mood) => (
                      <StoreMoodBadge name={mood.name} />
                    ))}
                  </FeedMoods>
                  <FeedIcon>
                    <div>
                      <HeartBgIcon />
                      <span>{feed.likeCount}</span>
                    </div>
                    <div>
                      <ChatDotsIcon />
                      <span>{feed.commentCount}</span>
                    </div>
                  </FeedIcon>
                </FeedBottom>
              </FeedInfo>
            </FeedItem>
          ))}
        </ul>
      </FeedsWrapper>

      <div>위로 올라가기</div>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  max-width: 568px;
  height: 100%;
  margin: 32px 0;
  position: relative;
  left: 50%;
  background-color: ${({ theme: { colors } }) => colors.white};
  transform: translateX(-50%);
  border: 1px solid ${({ theme: { colors } }) => colors.black};

  ${media.md} {
    width: 100%;
  }

  ${media.xs} {
    border-left: none;
    border-right: none;
    margin-top: 0;
  }
`;

const HeroImage = styled.div`
  position: relative;
`;

const MainThumnail = styled.div<{ src: string }>`
  height: 240px;
  width: 100%;
  background-image: url(${({ src }) => src});
  position: relative;
  background-size: cover;
  background-position: center;
  overflow: hidden;

  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.3);
  }
`;

const Author = styled.div`
  display: flex;
  align-items: center;
  gap: 12px;
  position: absolute;
  bottom: 16px;
  left: 16px;
`;

const UserName = styled.p`
  color: ${({ theme: { colors } }) => colors.white};
  font: ${({ theme: { fonts } }) => fonts.displayB16};
`;

const InfoWrapper = styled.div`
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 16px;
`;

const Info = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Title = styled.div`
  display: flex;
  gap: 16px;
  margin-bottom: 8px;

  h1 {
    font: ${({ theme: { fonts } }) => fonts.displayB16};
  }

  span {
    font: ${({ theme: { fonts } }) => fonts.displayM14};
    color: ${({ theme: { colors } }) => colors.textSecondary};
  }
`;

const Moods = styled.div`
  display: flex;
  gap: 8px;
  margin-left: auto;
`;

const ActionBar = styled.div`
  display: flex;
  justify-content: space-around;
  padding: 8px 0;
  border-top: 1px dashed black;
  border-bottom: 1px dashed black;

  button {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 8px;
    padding: 0 32px;
  }
`;

const FeedsWrapper = styled.section`
  h2 {
    font: ${({ theme: { fonts } }) => fonts.displayB16};
    margin-left: 16px;
    margin-bottom: 16px;

    span {
      color: ${({ theme: { colors } }) => colors.textSecondary};
    }
  }

  ul {
    display: flex;
    flex-direction: column;
  }
`;

const FeedInfo = styled.div`
  width: 100%;
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  justify-content: space-between;
`;

const FeedTop = styled.div`
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  gap: 8px;
`;

const FeedHeader = styled.div`
  display: flex;
  justify-content: space-between;
  font: ${({ theme: { fonts } }) => fonts.displayM16};
  align-items: center;
`;

const FeedTitle = styled.div`
  display: flex;
  gap: 8px;
  align-items: center;
`;

const FeedItem = styled.section`
  border-top: 1px solid black;
  display: flex;
  height: 164px;

  img {
    max-width: 164px;
    max-height: 164px;
    width: 100%;
    aspect-ratio: 1/1;
  }
`;

const FeedMoods = styled.div`
  display: flex;
  gap: 8px;
  overflow: hidden;
`;

const FeedBottom = styled.div`
  display: flex;
  justify-content: space-between;

  ${media.md} {
    flex-direction: column;
    gap: 8px;
  }
`;

const FeedIcon = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;

  div {
    display: flex;
    align-items: center;
    gap: 4px;
  }
`;
