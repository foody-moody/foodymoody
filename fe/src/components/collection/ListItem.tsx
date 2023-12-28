import { styled } from 'styled-components';
import { TextButton } from 'components/common/button/TextButton';
import {
  ChatDotsIcon,
  DotGhostIcon,
  HeartFillIcon,
} from 'components/common/icon/icons';
import { UserImage } from 'components/common/userImage/UserImage';
import { generateDefaultUserImage } from 'utils/generateDefaultUserImage';

type Props = {
  collections: any;
};

export const ListItem: React.FC<Props> = ({ collections }) => {
  return (
    <Wrapper>
      {collections.map((collection) => (
        <List key={collection.id}>
          <Thumbnail>
            <FeedCounter>{7}</FeedCounter>
            <img
              src={collection.imageUrl}
              alt={collection.imageUrl}
              onClick={() => {}}
            />
          </Thumbnail>
          <ListContent>
            <ContentHeader>
              <Title>{'겁나 맛있었던 곳 10선'}</Title>
              <TextButton color="black">
                <DotGhostIcon />
              </TextButton>
            </ContentHeader>
            <ContentBody>
              <ContentText>
                {'맛있었던 곳 10선을 소개합니다 설명설명'}
              </ContentText>
              <BadgeWrapper>{/* 배지 영역입니다  */}</BadgeWrapper>
            </ContentBody>
            <ContentBottom>
              <InfoLeft>
                <UserImage imageUrl={generateDefaultUserImage('얌')} />
                <ListUserName>{'산타'}</ListUserName>
              </InfoLeft>
              <InfoRight>
                <IconBox>
                  <HeartFillIcon />
                  <ListLikeCount>{11}</ListLikeCount>
                </IconBox>
                <IconBox>
                  <ChatDotsIcon />
                  <ListLikeCount>{4}</ListLikeCount>
                </IconBox>
              </InfoRight>
            </ContentBottom>
          </ListContent>
        </List>
      ))}
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
`;

const List = styled.div`
  display: flex;
  width: 100%;
  height: 135px;
  border-top: 1px solid ${({ theme: { colors } }) => colors.black};
  border-bottom: 1px solid ${({ theme: { colors } }) => colors.black};
  cursor: pointer;
  /* border-radius: 0px 0px 40px 0px; */
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

const Title = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.black};
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
  height: 20px;
  gap: 4px;
  width: 100%;
  border: 1px solid red;
  /* 배지 영역입니다 */
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
