import { styled } from 'styled-components';
import { flexRow } from 'styles/customStyle';
import { ServiceNotOpen } from 'components/common/help/ServiceNotOpen';

export const FeedLikeList = () => {
  return (
    <Wrapper>
      <ServiceNotOpen />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  ${flexRow};
  justify-content: center;
  padding: 20px 0 0 0;
`;
