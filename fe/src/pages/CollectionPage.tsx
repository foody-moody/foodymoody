import { useState } from 'react';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { CollectionContainer } from 'components/collection/CollectionContainer';
import { TextButton } from 'components/common/button/TextButton';
import { MenuIcon, StarLargeEmptyIcon } from 'components/common/icon/icons';

export const CollectionPage = () => {
  const [isGrid, setIsGrid] = useState(true);
  const handleSetGrid = () => {
    setIsGrid(true);
  };
  const handleSetList = () => {
    setIsGrid(false);
  };

  return (
    <Wrapper>
      <ContentWrapper>
        CollectionPage
        <TextButton color="black" onClick={handleSetGrid}>
          <StarLargeEmptyIcon />
        </TextButton>
        <TextButton color="black" onClick={handleSetList}>
          <MenuIcon />
        </TextButton>
        <CollectionContainer isGrid={isGrid}></CollectionContainer>
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
`;

const ContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 566px;
  height: 100%;
  border-left: 1px solid ${({ theme: { colors } }) => colors.black};
  border-right: 1px solid ${({ theme: { colors } }) => colors.black};

  ${media.md} {
    max-width: 568px;
    width: 100%;
  }

  ${media.xs} {
    border-left: none;
    border-right: none;
  }
`;
