import { styled } from 'styled-components';
import { GoogleIcon } from '../icon/icons';
import { PATH } from 'constants/path';

const { MODE, VITE_GOOGLE_CLIENT_ID } = import.meta.env;

export const OAuthButton = () => {
    const isDev = MODE === 'development';
    console.log('isDev', isDev);

    const LOCAL_URL = 'http://localhost:5173';
    // const GOOGLE_URL = `https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email&client_id=${VITE_GOOGLE_CLIENT_ID}&response_type=code&redirect_uri=${VITE_REDIRECT_ADDRESS}&access_type=offline`;

    // const GOOGLE_URL = `https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email&client_id=${VITE_GOOGLE_CLIENT_ID}&response_type=code&redirect_uri=${
    //     isDev ? LOCAL_URL + END_POINT.OAuthLogin : 'https://foodymoody.store' + END_POINT.OAuthLogin
    // }&access_type=offline`;
    // console.log('RedirectAddress', 'https://foodymoody.store' + END_POINT.OAuthLogin);

    const GOOGLE_URL = `https://accounts.google.com/o/oauth2/v2/auth?
    	client_id=${VITE_GOOGLE_CLIENT_ID}
    	&redirect_uri=${isDev ? LOCAL_URL + PATH.GOOGLE : 'https://foodymoody.store/api' + PATH.GOOGLE}
    	&response_type=code
    	&scope=email profile`;
    const handleOauthLogin = () => {
        location.replace(GOOGLE_URL); // 이동
        // window.open(GOOGLE_URL, '_blank', 'width=500,height=600,left=50,top=10'); // 새창
    };

    return (
        <Wrapper onClick={handleOauthLogin} type="button">
            <GoogleIcon />
            <Text>Google로 계속하기</Text>
        </Wrapper>
    );
};

const Wrapper = styled.button`
    width: 100%;
    display: flex;
    align-items: center;
    height: 48px;
    padding: 16px;
    border-radius: 4px;

    cursor: pointer;
    border: 1px solid ${({ theme: { colors } }) => colors.textTertiary};
    background-color: ${({ theme: { colors } }) => colors.white};
    transition: all 0.2s ease-in-out;
    &:hover {
        background-color: ${({ theme: { colors } }) => colors.bgGray50};
    }
`;

const Text = styled.p`
    font: ${({ theme: { fonts } }) => fonts.displayB14};
    color: ${({ theme: { colors } }) => colors.textSecondary};
    flex: 1;
`;
