import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { CollectionContainer } from 'components/collection/CollectionContainer';
// import { GridItem } from 'components/collection/GridItem';
import { LayoutButton } from 'components/layoutButton/LayoutButton';

export const CollectionPage = () => {
  return (
    <Wrapper>
      {/* <BannerContent /> */}
      <ContentWrapper>
        <HeaderContent>
          <SubTitle>오늘의 컬렉션🎉</SubTitle>
          <CollectionSlick>
            {/* <GridItem collections={MOCK_FEEDS} /> */}
          </CollectionSlick>
        </HeaderContent>
        <BodyContent>
          <Header>
            <HeaderLeft>
              <SubTitle>컬렉션 둘러보기✨</SubTitle>
            </HeaderLeft>
            <HeaderRight>
              <Text>푸디무디들이 엄선한 맛집모음!</Text>
              <SortBox>
                <Dummy>{/* 셀렉트component자리*/}</Dummy>
                <LayoutButton />
              </SortBox>
            </HeaderRight>
          </Header>
          <CollectionContainer />
        </BodyContent>
      </ContentWrapper>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  transition: all 0.2s ease-in-out;
`;

const ContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 56px;
  width: 566px;
  height: 100%;
  /* border-left: 1px solid ${({ theme: { colors } }) => colors.black};
  border-right: 1px solid ${({ theme: { colors } }) => colors.black}; */

  ${media.md} {
    max-width: 568px;
    width: 100%;
  }

  ${media.xs} {
    border-left: none;
    border-right: none;
  }
`;

/* 컬렉션 */

// const generateDefaultImage = (imageUrl: string) =>
//   `https://source.boringavatars.com/beam/${imageUrl}?colors=FF4E50,FC913A,F9D423,EDE574,E1F5C4&square`;

// const MOCK_FEEDS = Array.from({ length: 3 }, (_, index) => ({
//   id: index + 1,
//   imageUrl: generateDefaultImage(`githubrandomProfileimageurl${index + 1}`),
// }));

const HeaderContent = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 24px;
  gap: 16px;
`;

const SubTitle = styled.h2`
  font: ${({ theme: { fonts } }) => fonts.displayB20};
`;

const CollectionSlick = styled.div`
  display: flex;
  justify-content: center;
`;

const BodyContent = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  gap: 16px;
`;

const Header = styled.div`
  display: flex;
  flex-direction: column;
  /* gap: 4px; */
  /* justify-content: space-between;
  align-items: center; */
`;

const HeaderLeft = styled.div`
  display: flex;
  align-items: center;
`;

const HeaderRight = styled.div`
  display: flex;
  align-items: center;
  gap: 16px;
  /* justify-content: flex-end; */
  justify-content: space-between;
`;

const Text = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
`;

const SortBox = styled.div`
  display: flex;
  align-items: center;
  gap: 16px;
`;

const Dummy = styled.div`
  width: 120px;
  height: 30px;
  background: white;
  border: 1px solid black;
  border-radius: 4px;
`;

// const BannerContent = styled.div`
//   background: ${({ theme: { colors } }) => colors.blue500};
//   width: 80%;
//   height: 100px;
//   border-radius: 4px;
// `;
