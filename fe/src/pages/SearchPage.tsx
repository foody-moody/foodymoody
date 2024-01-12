import { styled } from 'styled-components';
import { ServiceNotOpen } from 'components/common/help/ServiceNotOpen';

export const SearchPage = () => {
  return (
    <Wrapper>
      <ServiceNotOpen />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  height: 100dvh;
  position: relative;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
