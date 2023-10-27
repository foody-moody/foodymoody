import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { Badge } from '../common/badge/Badge';
import { Button } from '../common/button/Button';
import { CollectableAddIcon } from '../common/icon/icons';
import { UserImageEdit } from '../common/userImage/UserImageEdit';

type Props = {
  member: ProfileMemberInfoType;
};

const MOCK_BADGE = {
  id: 0,
  name: '도전적인',
};

export const ProfileUserInfo: React.FC<Props> = ({ member }) => {
  const handleAddCollection = () => {};
  const handleEditProfile = () => {};

  return (
    <Wrapper>
      <ContentLeft>
        <UserImageEdit imageUrl={member.imageUrl} />
        <Column>
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
        </Column>
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

const Flex = styled.div`
  display: flex;
`;

const Column = styled(Flex)`
  flex-direction: column;
`;

const Wrapper = styled(Column)`
  width: 100%;
  gap: 16px;
`;

const ContentWrapper = styled(Flex)`
  width: fit-content;
  align-items: center;
  gap: 8px;
`;

const ContentLeft = styled(Flex)`
  justify-content: center;
  align-items: center;
  gap: 32px;

  ${media.xs} {
    gap: 16px;
  }
`;

const ContentHeader = styled(Flex)`
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

const ContentBody = styled(Flex)`
  gap: 16px;
  width: 280px;
  justify-content: space-between;
  ${media.xs} {
    width: 206px;
  }
`;

const ButtonBox = styled(Flex)`
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
