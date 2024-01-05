import { useState } from 'react';
import { useToast } from 'recoil/toast/useToast';
import { styled } from 'styled-components';
import { flexColumn, flexRow } from 'styles/customStyle';
import { media } from 'styles/mediaQuery';
import {
  FaceIcon,
  HeartFillIcon,
  MapPinLargeIcon,
  StarLargeFillIcon,
} from 'components/common/icon/icons';

export const StorePage = () => {
  const [activeTab, setActiveTab] = useState('home');
  const toast = useToast();

  const handleTabClick = (tab: 'home' | 'feed') => {
    setActiveTab(tab);
  };

  const address = '경기도 안양시 동안구 평촌대로223번길 16 (호계동) 2층 205호';

  const handleCopyToClipBoard = async () => {
    try {
      await navigator.clipboard.writeText(address); //TODO실제 데이터의 가게주소로 변경 store.address
      toast.noti('복사되었습니다');
    } catch (e) {
      toast.noti('다시 시도해주세요');
    }
  };

  return (
    <Wrapper>
      <ContentWrapper>
        <Header>
          <HeaderTitle>
            <FlexRow>
              <StoreTitle>{'후타가와 짜장'}</StoreTitle>
              <Rating>
                <StarLargeFillIcon />
                <RatingCount>{4.5}</RatingCount>
              </Rating>
            </FlexRow>
            <TitleText>
              <SubText>{'범계동'}</SubText>
              <SubText>{'·'}</SubText>
              <SubText>{'피드 10'}</SubText>
            </TitleText>
          </HeaderTitle>
          <HeartFillIcon />
        </Header>
        <Tab>
          <TabItem
            onClick={() => handleTabClick('home')}
            $tab={activeTab === 'home'}
          >
            홈
          </TabItem>
          <TabItem
            onClick={() => handleTabClick('feed')}
            $tab={activeTab === 'feed'}
          >
            피드
          </TabItem>
        </Tab>
        <MapContent>
          <MapContainer>
            <SubTile>정보</SubTile>
            <Map></Map>
          </MapContainer>
          <InfoContainer>
            <Info>
              <MapPinLargeIcon />
              <AddressContainer>
                <Address>
                  {'경기도 안양시 동안구 평촌대로223번길 16 (호계동) 2층 205호'}
                </Address>
                <CopyContainer onClick={handleCopyToClipBoard}>
                  <FaceIcon />
                  <Copy>복사하기</Copy>
                </CopyContainer>
              </AddressContainer>
            </Info>
            <Info>
              <MapPinLargeIcon />
              <Address>{'031-383-6333'}</Address>
            </Info>
          </InfoContainer>
        </MapContent>
      </ContentWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  transition: all 0.2s ease-in-out;

  //추가한 부분
  align-items: center;
`;

const ContentWrapper = styled.div`
  width: 566px;
  height: 100%;
  border-left: 1px solid ${({ theme: { colors } }) => colors.black};
  border-right: 1px solid ${({ theme: { colors } }) => colors.black};
  background: ${({ theme: { colors } }) => colors.white};
  //추가한 부분
  margin-top: 40px;
  padding: 16px;
  box-sizing: border-box;
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  border-radius: 4px;
  ${flexColumn}
  gap: 20px;

  ${media.md} {
    max-width: 568px;
    width: 100%;
    //추가한 부분
    margin-bottom: 40px;
  }

  ${media.xs} {
    border-left: none;
    border-right: none;

    //추가한 부분
    margin-top: 0;
    margin-bottom: 55px;
  }
`;

const Header = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
`;

const HeaderTitle = styled.div`
  ${flexColumn}
  width: 100%;
`;

const FlexRow = styled.div`
  ${flexRow}
  align-items: center;
  gap: 8px;
`;

const StoreTitle = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayB20};
`;

const Rating = styled.div`
  ${flexRow}
  align-items: center;
  gap: 4px;
  cursor: default;
`;

const RatingCount = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
`;

const TitleText = styled.div`
  ${flexRow}
  gap: 4px;
`;

const SubText = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
`;

const Tab = styled.div`
  ${flexRow}
  width: 100%;
  height: 48px;
`;

const TabItem = styled.div<{ $tab: boolean }>`
  flex: 1;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-bottom: 1px solid
    ${({ $tab, theme: { colors } }) => ($tab ? colors.orange : colors.black)};
  color: ${({ $tab, theme: { colors } }) =>
    $tab ? colors.orange : colors.black};
  font: ${({ $tab, theme: { fonts } }) =>
    $tab ? fonts.displayB16 : fonts.displayM16};
  transition: all 0.2s ease-in-out;
`;

const MapContent = styled.div`
  ${flexColumn}
  width: 100%;
  height: 100%;
  gap: 20px;
`;

const MapContainer = styled.div`
  width: 100%;
  ${flexColumn}
  gap: 8px;
`;

const SubTile = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
`;

const Map = styled.div`
  width: 100%;
  height: 400px; // media
  border: 1px solid ${({ theme: { colors } }) => colors.black};
  ${media.md} {
    height: 400px;
  }

  ${media.xs} {
    height: 320px;
  }
`;

const InfoContainer = styled.div`
  ${flexColumn}
  width: 100%;
  height: 100%;
  gap: 16px;
`;

const Info = styled.div`
  ${flexRow}
  width: 100%;
  /* height: 48px; */
  gap: 8px;
`;

const AddressContainer = styled.p`
  ${flexRow}
  flex-wrap: wrap;
  width: 100%;
  gap: 4px;
`;

const Address = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM14};
`;

const CopyContainer = styled.div`
  ${flexRow}
  align-items: center;
  gap: 4px;
  cursor: pointer;
`;

const Copy = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayM12};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
