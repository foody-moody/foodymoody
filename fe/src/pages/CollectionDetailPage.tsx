import { useParams } from 'react-router-dom';
import {
  useDeleteFeedFromCollection,
  useGetCollectionDetail,
} from 'service/queries/collection';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { StoreMoodBadge } from 'components/common/badge/StoreMoodBadge';
import { Dropdown } from 'components/common/dropdown/Dropdown';
import { DropdownRow } from 'components/common/dropdown/DropdownRow';
import {
  DotGhostIcon,
  HeartSmallEmpty,
  ShareIcon,
  StoreIcon,
  HeartBgIcon,
  ChatDotsIcon,
  TrashIcon,
} from 'components/common/icon/icons';
import { useModal } from 'components/common/modal/useModal';
import { UserImage } from 'components/common/userImage/UserImage';
import { FollowListButton } from 'components/follow/followButton/FollowListButton';
import { useAuthState } from 'hooks/auth/useAuth';
import { formatTimeStamp } from 'utils/formatTimeStamp';

/** TODO
 * 1. 기본 정보 수정 기능
 * 2. 글 삭제
 * 3. 좋아요 기능
 * 4. 공유하기
 * 5. 댓글 (?)
 */

export const CollectionDetailPage = () => {
  // TODO. private 글이면 접근 못하게 해야함
  const { id } = useParams() as { id: string };
  const { openModal, closeModal } = useModal<'collectionAlert'>();

  const { userInfo } = useAuthState();

  const { data: collection, isLoading } = useGetCollectionDetail(id);
  const { mutate: deleteFeed } = useDeleteFeedFromCollection();
  console.log(collection);

  if (isLoading) return <p>로딩중</p>; // 임시 로딩중

  const isMe = userInfo?.id === collection.author.id;

  const handleDeleteFeed = (feedId: string) => {
    deleteFeed(
      { collectionId: id, feedId },
      {
        onSuccess: () => {
          closeModal('collectionAlert');
        },
      }
    );
  };
  console.log(userInfo, 'userInfo');

  return (
    <Wrapper>
      <HeroImage>
        <MainThumnail
          src={
            collection?.thumbnailUrl ||
            'https://images.unsplash.com/photo-1606787366850-de6330128bfc?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
          }
        />
        <Author>
          <UserImage imageUrl={collection.author.profileImageUrl} />
          <UserName>{collection.author.name}</UserName>
          {!isMe && (
            <FollowListButton
              memberId={collection.author.id}
              isFollowing={false}
              size="xs"
              width={80}
            />
          )}
        </Author>
      </HeroImage>

      <InfoWrapper>
        <Info>
          <div>
            <Title>
              <h1>{collection.title}</h1>
              <span>{formatTimeStamp(collection.createdAt)}</span>
            </Title>
            <p>{collection.description}</p>
          </div>
          <div>
            {isMe && (
              <Dropdown align="right" opener={<DotGhostIcon />}>
                <DropdownRow>수정하기</DropdownRow>
                <DropdownRow
                  onClick={() =>
                    openModal('collectionAlert', {
                      title: '현재 컬렉션을 삭제하시겠습니까?',
                      onConfirm: () => {},
                    })
                  }
                >
                  삭제하기
                </DropdownRow>
              </Dropdown>
            )}
          </div>
        </Info>

        <Moods>
          {collection.moods.map((mood: { id: string; name: string }) => (
            <StoreMoodBadge name={mood.name} key={mood.id} />
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
          피드들 <span>{collection.feeds.length}</span>
        </h2>

        <ul>
          {/* 수정되면 type 박아용 */}
          {/* eslint-disable-next-line @typescript-eslint/no-explicit-any */}
          {collection.feeds.map((feed: any) => (
            <FeedItem key={feed.id}>
              <img src={feed.thumbnailUrl} alt="" />
              <FeedInfo>
                <FeedTop>
                  <FeedHeader>
                    <FeedTitle>
                      <StoreIcon /> <h3>가게 이름이 올거에요~~</h3>
                    </FeedTitle>
                    {isMe && (
                      <TrashIcon
                        onClick={() =>
                          openModal('collectionAlert', {
                            title: '해당 피드를 컬렉션에서 삭제하시겠습니까?',
                            onConfirm: () => handleDeleteFeed(feed.id),
                          })
                        }
                      />
                    )}
                  </FeedHeader>
                  <p>{feed.content}</p>
                </FeedTop>
                <FeedBottom>
                  <FeedMoods>
                    {feed.storeMood.map((mood: Badge) => (
                      <StoreMoodBadge name={mood.name} key={mood.id} />
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

      {/* <div>위로 올라가기</div> */}
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
  flex-wrap: wrap;
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
