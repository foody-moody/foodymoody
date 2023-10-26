import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { Badge } from '../common/badge/Badge';
import { Button } from '../common/button/Button';
import { CollectableAddIcon } from '../common/icon/icons';
import { UserImageEdit } from '../common/userImage/UserImageEdit';

type Props = {
  member: ProfileMemberInfoType;
};

export const ProfileUserInfo: React.FC<Props> = ({ member }) => {
  const handleAddCollection = () => {};

  const handleEditProfile = () => {};

  const MOCK_BADGE = {
    id: 0,
    name: '도전적인',
  };

  return (
    <Wrapper>
      <ContentLeft>
        <UserImageEdit imageUrl={member.imageUrl} />
        <FlexColumnBox>
          <ContentHeader>
            <p>{member.nickname}</p>
            <Badge badge={MOCK_BADGE} variant="taste" />
          </ContentHeader>

          <ContentBody>
            <InfoItem>
              {member.myFeedsCount}
              <span>게시물</span>
            </InfoItem>
            <InfoItem>
              {member.myFeedsCount}
              <span>팔로잉</span>
            </InfoItem>
            <InfoItem>
              {member.myFeedsCount}
              <span>팔로워</span>
            </InfoItem>
          </ContentBody>
        </FlexColumnBox>
      </ContentLeft>
      <ButtonBox>
        <Button size="s" backgroundColor="orange" onClick={handleAddCollection}>
          <CollectableAddIcon />
          <span>나만의 컬렉션 만들기</span>
        </Button>
        <Button
          size="s"
          backgroundColor="white"
          width={114}
          onClick={handleEditProfile}
        >
          <span>프로필 수정</span>
        </Button>
      </ButtonBox>
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

const Wrapper = styled(FlexColumnBox)`
  width: 100%;
  gap: 24px;
`;

const ContentWrapper = styled(FlexRowBox)`
  width: fit-content;
  align-items: center;
  gap: 8px;
`;

const ContentLeft = styled(FlexRowBox)`
  justify-content: center;
  align-items: center;
  gap: 32px;

  ${media.xs} {
    gap: 16px;
  }
`;

const ContentHeader = styled(FlexRowBox)`
  align-items: center;
  justify-content: space-between;

  p {
    font: ${({ theme }) => theme.fonts.displayM20};
  }

  span {
    font: ${({ theme }) => theme.fonts.displayM12};
    color: ${({ theme }) => theme.colors.textSecondary};
  }
`;

const ContentBody = styled(FlexRowBox)`
  gap: 16px;
  width: 280px;
  justify-content: space-between;

  ${media.xs} {
    width: 206px;
  }
`;

const ButtonBox = styled(FlexRowBox)`
  gap: 8px;
  span {
    font: ${({ theme: { fonts } }) => fonts.displayM14};
  }

  ${media.xs} {
    span {
      font: ${({ theme: { fonts } }) => fonts.displayM12};
    }
  }
`;

const InfoItem = styled(ContentWrapper)`
  gap: 4px;
  font: ${({ theme: { fonts } }) => fonts.displayB14};

  span {
    font: ${({ theme: { fonts } }) => fonts.displayM14};
    color: ${({ theme: { colors } }) => colors.textSecondary};
  }
`;
