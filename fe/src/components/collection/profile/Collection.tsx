import { useParams } from 'react-router-dom';
import { useSort } from 'recoil/sortState/useSort';
import { useGetProfileCollection } from 'service/queries/collection';
import { styled } from 'styled-components';
import { LayoutButton } from 'components/layoutButton/LayoutButton';
import { SelectSort } from 'components/sort/SelectSort';
import { useAuthState } from 'hooks/auth/useAuth';
import { CollectionContainer } from './CollectionContainer';

export const Collection = () => {
  const { id } = useParams();
  const { userInfo } = useAuthState();
  const USER_ID = id || userInfo?.id;
  const { sortBy } = useSort('profileCollection');
  const { collections, count, author, hasNextPage, fetchNextPage } =
    useGetProfileCollection(USER_ID, sortBy);

  return (
    <Wrapper>
      <Header>
        <HeaderLeft>
          <Title>콜렉션</Title>
          <CollectionCounter>{count}</CollectionCounter>
        </HeaderLeft>
        <SortContainer>
          <SelectSort sortId="profileCollection" />
          <LayoutButton />
        </SortContainer>
      </Header>
      <Contents>
        <CollectionContainer
          collections={collections}
          hasNextPage={hasNextPage}
          fetchNextPage={fetchNextPage}
          author={author}
        />
      </Contents>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  /* height: 100dvh; */
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

const SortContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 16px;
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
