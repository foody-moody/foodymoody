import { styled } from 'styled-components';
import { Register } from 'components/register/Register';

export const RegisterPage = () => {
  return (
    <Wrapper>
      <Register />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;
