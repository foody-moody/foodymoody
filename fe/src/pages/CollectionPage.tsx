import { useSetLayout } from 'recoil/booleanState/useSetLayout';
import { styled } from 'styled-components';
import { media } from 'styles/mediaQuery';
import { CollectionContainer } from 'components/collection/CollectionContainer';
import { LayoutButton } from 'components/layoutButton/LayoutButton';

export const CollectionPage = () => {
  const { isOn } = useSetLayout();

  return (
    <Wrapper>
      <ContentWrapper>
        CollectionPage
        <LayoutButton />
        <CollectionContainer isGrid={isOn} />
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
