import { useNavigate } from 'react-router-dom';
import { useDeleteFeed } from 'service/queries/feed';
import { styled } from 'styled-components';
import { useModal } from 'components/common/modal/useModal';
import { useAuthState } from 'hooks/auth/useAuth';
import { formatTimeStamp } from 'utils/formatTimeStamp';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';
import { TasteMoodBadge } from '../badge/TasteMoodBadge';
import { Dropdown } from '../dropdown/Dropdown';
import { DropdownRow } from '../dropdown/DropdownRow';
import { DotGhostIcon, MapPinSmallIcon } from '../icon/icons';
import { UserImage } from '../userImage/UserImage';
import { Share } from './Share';
import { PATH } from 'constants/path';

type Props = {
  feedId?: string;
  member: FeedMemberInfo;
  createdAt: string;
  isUpdated: string | null;
  store: Store;
  thumbnail?: string;
};

export const FeedUserInfo: React.FC<Props> = ({
  feedId,
  member,
  createdAt,
  isUpdated,
  store,
  thumbnail,
}) => {
  const navigate = useNavigate();
  const { mutate: deleteMutate } = useDeleteFeed();
  const { isLogin, userInfo } = useAuthState();
  const formattedTimeStamp = formatTimeStamp(createdAt);
  const isAuthor = userInfo?.id === member.id;
  const { openModal } = useModal<'collection'>();

  const handleNavigateProfile = () => {
    isAuthor
      ? navigate(PATH.PROFILE)
      : navigate(PATH.PROFILE + '/' + member.id);
    sessionStorage.setItem('profileId', member.id);
  };

  const hadleNavigateStore = () => {
    navigate(PATH.STORE + '/' + store.id);
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
        navigate(`${PATH.PROFILE}/${member.id}`);
      },
    }, // 일단 프로필로 이동
  ];

  const privateMenu = [
    {
      id: 1,
      content: '컬렉션 추가하기',
      onClick: () => {
        openModal('collection', { type: 'addFeed', feedId: feedId });
      },
    },
    {
      id: 2,
      content: '수정하기',
      onClick: () => {
        navigate(`${PATH.EDIT_FEED}/${feedId}`, {
          state: { background: 'newFeed' },
        });
      },
    },
    {
      id: 3,
      content: '삭제하기',
      onClick: () => {
        feedId && deleteMutate(feedId);
      },
    },
  ];

  const menu = isLogin && userInfo?.id === member.id ? privateMenu : publicMenu;

  return (
    <Wrapper>
      <ContentLeft>
        <UserImage
          imageUrl={
            member.profileImageUrl || generateDefaultUserImage(member.id)
          }
          onClick={handleNavigateProfile}
        />
        <FlexColumnBox>
          <ContentHeader>
            <p>{member.nickname}</p>
            <span>{formattedTimeStamp}</span>
            {isUpdated && <span>수정됨</span>}
          </ContentHeader>

          <ContentBody onClick={hadleNavigateStore}>
            <MapPinSmallIcon />
            <p>{store.name || '이름'}</p> {/* TODO store.name null 수정 */}
          </ContentBody>
        </FlexColumnBox>
      </ContentLeft>

      <ContentRight>
        <TasteMoodBadge name={member.tasteMood.name} />

        <Dropdown align="right" opener={<DotGhostIcon />}>
          <DropdownRow>
            <Share imageUrl={thumbnail} targetId={feedId} />
          </DropdownRow>
          {menu.map((item) => (
            <DropdownRow key={item.id} onClick={item.onClick}>
              {item.content}
            </DropdownRow>
          ))}
        </Dropdown>
      </ContentRight>
    </Wrapper>
  );
};

export const FlexRowBox = styled.div`
  display: flex;
`;

export const FlexColumnBox = styled.div`
  display: flex;
  flex-direction: column;
`;

const Wrapper = styled(FlexRowBox)`
  width: 100%;
  justify-content: space-between;
`;

const ContentWrapper = styled(FlexRowBox)`
  width: fit-content;
  align-items: center;
  gap: 8px;
`;

const ContentHeader = styled(ContentWrapper)`
  align-items: center;
  p {
    font: ${({ theme }) => theme.fonts.displayM16};
  }
  span {
    font: ${({ theme }) => theme.fonts.displayM12};
    color: ${({ theme }) => theme.colors.textSecondary};
  }
`;

const ContentBody = styled(ContentWrapper)`
  align-items: center;
  gap: 4px;
  cursor: pointer;
  p {
    font: ${({ theme }) => theme.fonts.displayM12};
    color: ${({ theme }) => theme.colors.textSecondary};
  }
`;

const ContentRight = styled(FlexRowBox)`
  align-items: flex-start;
  gap: 8px;

  svg {
    cursor: pointer;
  }
`;

const ContentLeft = styled(ContentWrapper)`
  gap: 16px;
`;
