import { forwardRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { StoreMoodBadge } from 'components/common/badge/StoreMoodBadge';
import { Dropdown } from 'components/common/dropdown/Dropdown';
import { DropdownRow } from 'components/common/dropdown/DropdownRow';
import {
  ChatDotsIcon,
  DotGhostIcon,
  HeartFillIcon,
} from 'components/common/icon/icons';
import { UserImage } from 'components/common/userImage/UserImage';
import { useAuthState } from 'hooks/auth/useAuth';
import { formatTimeStamp } from 'utils/formatTimeStamp';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { PATH } from 'constants/path';

type Props = {
  collection: CollectionItem;
  profileAuthor?: Author;
};

export const ListItem = forwardRef<HTMLLIElement, Props>(
  ({ collection, profileAuthor }, ref) => {
    const { isLogin, userInfo } = useAuthState();
    const isAuthor =
      userInfo?.id === (collection?.author?.id || profileAuthor?.id);
    const navigate = useNavigate();

    const handleNavigateToDetail = (id: string) => {
      navigate(PATH.COLLECTION + '/' + id);
    };

    const handleNavigateToProfile = (id: string) => {
      isAuthor ? navigate(PATH.PROFILE) : navigate(PATH.PROFILE + '/' + id);
      sessionStorage.setItem('profileId', id);
    };

    const timeStamp = (createdAt: string, updatedAt: string) => {
      if (createdAt === updatedAt) {
        return formatTimeStamp(createdAt);
      } else {
        return `${formatTimeStamp(updatedAt)} 업데이트`;
      }
    };

    const publicMenu = [
      {
        id: 1,
        content: '신고하기',
        onClick: () => {},
      },

      {
        id: 2,
        content: '팔로우',
        onClick: () => {
          navigate(
            `${PATH.PROFILE}/${collection?.author?.id || profileAuthor?.id}`
          );
        },
      },
    ];

    const privateMenu = [
      {
        id: 1,
        content: '수정하기',
        onClick: () => {},
      },
      {
        id: 2,
        content: '삭제하기',
        onClick: () => {
          // feedId && deleteMutate(feedId);
        },
      },
    ];

    const menu =
      isLogin && userInfo?.id === (collection?.author?.id || profileAuthor?.id)
        ? privateMenu
        : publicMenu;

    return (
      <Wrapper key={collection.id} ref={ref}>
        <Thumbnail
          onClick={() => {
            handleNavigateToDetail(collection.id);
          }}
        >
          <FeedCounter>{collection.feedCount}</FeedCounter>
          <img
            src={
              !collection.thumbnailUrl || collection.feedCount === 0
                ? generateDefaultUserImage(collection.id)
                : collection.thumbnailUrl
            }
            alt="thumbnail"
            onClick={() => {}}
          />
        </Thumbnail>
        <ListContent>
          <ContentHeader>
            <TitleContainer
              onClick={() => {
                handleNavigateToDetail(collection.id);
              }}
            >
              <Title>{collection.title}</Title>
              <TimeStamp>
                {timeStamp(collection.createdAt, collection.updatedAt)}
              </TimeStamp>
            </TitleContainer>
            {/* <TextButton color="black">
                  <DotGhostIcon />
                </TextButton> */}
            <Dropdown align="right" opener={<DotGhostIcon />}>
              {/* <DropdownRow>
                    <Share imageUrl={thumbnail} targetId={feedId} />
                  </DropdownRow> */}
              {menu.map((item) => (
                <DropdownRow key={item.id} onClick={item.onClick}>
                  {item.content}
                </DropdownRow>
              ))}
            </Dropdown>
          </ContentHeader>
          <ContentBody
            onClick={() => {
              handleNavigateToDetail(collection.id);
            }}
          >
            <ContentText>{collection.title}</ContentText>

            <BadgeWrapper>
              {collection.storeMood?.map((mood) => (
                <StoreMoodBadge name={mood.name} key={mood.id} />
              )) ||
                collection.moods?.map((mood) => (
                  <StoreMoodBadge name={mood.name} key={mood.id} />
                ))}
            </BadgeWrapper>
          </ContentBody>
          <ContentBottom>
            <InfoLeft
              onClick={() => {
                handleNavigateToProfile(
                  collection?.author?.id || (profileAuthor?.id as string)
                );
              }}
            >
              <UserImage
                imageUrl={
                  collection?.author?.profileImageUrl ||
                  profileAuthor?.profileImageUrl ||
                  generateDefaultUserImage('얌')
                }
              />
              <ListUserName>
                {collection?.author?.name || profileAuthor?.name}
              </ListUserName>
            </InfoLeft>
            <InfoRight>
              <IconBox>
                <HeartFillIcon />
                <ListLikeCount>{collection.likeCount}</ListLikeCount>
              </IconBox>
              <IconBox>
                <ChatDotsIcon
                  onClick={() => {
                    handleNavigateToDetail(collection.id);
                  }}
                />
                <ListLikeCount>{collection.commentCount}</ListLikeCount>
              </IconBox>
            </InfoRight>
          </ContentBottom>
        </ListContent>
      </Wrapper>
    );
  }
);

const Wrapper = styled.li`
  display: flex;
  width: 100%;
  height: 135px;
  border-top: 1px solid ${({ theme: { colors } }) => colors.black};
  border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};
  cursor: pointer;
`;

const Thumbnail = styled.div`
  position: relative;
  width: 135px;
  height: 135px;

  img {
    width: 134px;
    aspect-ratio: 1/1;
    object-fit: cover;
    cursor: pointer;
    border-right: 1px solid ${({ theme: { colors } }) => colors.black};
    border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};
  }
`;
const FeedCounter = styled.div`
  position: absolute;
  top: 8px;
  right: 8px;
  width: 18px;
  height: 18px;
  display: flex;
  justify-content: center;
  align-items: center;
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  background: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: ${({ theme: { radius } }) => radius.half};
`;

const ListContent = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  gap: 4px;
  padding: 12px;
  background: ${({ theme: { colors } }) => colors.white};
`;

const ContentHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const TitleContainer = styled.div`
  display: flex;
  gap: 8px;
`;

const Title = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.black};
`;

const TimeStamp = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;

const ContentBody = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
`;

const ContentText = styled.div`
  width: 100%;
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.black};
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const BadgeWrapper = styled.div`
  display: flex;
  height: fit-content;
  gap: 4px;
`;

const ContentBottom = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const InfoLeft = styled.div`
  display: flex;
  gap: 8px;
  height: 40px;
  align-items: center;
`;

const InfoRight = styled.div`
  display: flex;
  gap: 8px;
  align-self: flex-end;
`;

const ListUserName = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.black};
  white-space: nowrap;
`;

const ListLikeCount = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
  color: ${({ theme: { colors } }) => colors.black};
`;

const IconBox = styled.div`
  display: flex;
  gap: 2px;
  align-items: center;
`;
