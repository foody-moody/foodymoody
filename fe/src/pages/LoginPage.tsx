import { styled } from 'styled-components';
import { Login } from 'components/login/Login';

export const LoginPage = () => {
  return (
    <Wrapper>
      <Login />
    </Wrapper>
  );
};

const Wrapper = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;
