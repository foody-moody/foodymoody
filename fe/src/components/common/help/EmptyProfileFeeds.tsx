import { styled } from 'styled-components';

export const EmptyProfileFeeds = () => {
  return (
    <Wrapper>
      <Text>아직 작성한 피드가 없어요.</Text>
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-top: 1px solid ${({ theme: { colors } }) => colors.black};
`;

const Text = styled.p`
  font: ${({ theme: { fonts } }) => fonts.displayB16};
  color: ${({ theme: { colors } }) => colors.textSecondary};
`;
