import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { CollectionContainer } from 'components/collection/CollectionContainer';
import { CollectionCarousel } from 'components/common/carousel/CollectionCarousel';
import { LayoutButton } from 'components/layoutButton/LayoutButton';
import { SelectSort } from 'components/sort/SelectSort';

export const CollectionPage = () => {
  return (
    <Wrapper>
      {/* <BannerContent /> */}
      <ContentWrapper>
        <HeaderContent>
          <SubTitle>오늘의 컬렉션🎉</SubTitle>
          <CollectionCarousel />
        </HeaderContent>
        <BodyContent>
          <Header>
            <HeaderLeft>
              <SubTitle>컬렉션 둘러보기✨</SubTitle>
            </HeaderLeft>
            <HeaderRight>
              <Text>푸디무디들이 엄선한 맛집모음!</Text>
              <SortContainer>
                <SelectSort sortId="collection" />
                <LayoutButton />
              </SortContainer>
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

  ${media.md} {
    max-width: 568px;
    width: 100%;
  }

  ${media.xs} {
    border-left: none;
    border-right: none;
  }
`;

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
`;

const HeaderLeft = styled.div`
  display: flex;
  align-items: center;
`;

const HeaderRight = styled.div`
  display: flex;
  align-items: center;
  gap: 16px;
  justify-content: space-between;
`;

const Text = styled.div`
  font: ${({ theme: { fonts } }) => fonts.displayM16};
`;

const SortContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 16px;
`;

// const BannerContent = styled.div`
//   background: ${({ theme: { colors } }) => colors.blue500};
//   width: 80%;
//   height: 100px;
//   border-radius: 4px;
// `;
