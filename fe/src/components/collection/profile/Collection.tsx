import { styled } from 'styled-components';
import { LayoutButton } from 'components/layoutButton/LayoutButton';
import { CollectionContainer } from '../CollectionContainer';

export const Collection = () => {
  return (
    <Wrapper>
      <Header>
        <HeaderLeft>
          <Title>콜렉션</Title>
          <CollectionCounter>{12}</CollectionCounter>
        </HeaderLeft>
        <LayoutButton />
      </Header>
      <Contents>
        <CollectionContainer />
      </Contents>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;

const Header = styled.div`
  border-top: 1px solid ${({ theme: { colors } }) => colors.black};
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
`;

const HeaderLeft = styled.div`
  display: flex;
  align-items: center;
  gap: 4px;
`;

const Title = styled.h2`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.black};
`;

const CollectionCounter = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB14};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
const Contents = styled.div`
  width: 100%;
  height: 100%;
`;
