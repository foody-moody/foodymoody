import { useSearchParams } from 'react-router-dom';
import { useOAuthLogin } from 'service/queries/auth';

export const OAuthRedirectPage = () => {
  const [params] = useSearchParams();
  const code = params.get('code') as string;
  console.log('code', code);
  // const { refetch: fetchOAuth, data } = useOAuthLogin(code);
  //mutate작성
  useOAuthLogin(code);
  // useEffect(() => {
  //   if (code) {
  //     //mutate실행
  //     try {
  //       fetchOAuth();
  //     } catch (error) {
  //       console.error('error', error);
  //     }
  //   }
  // }, [code]);

  // const handleOauthLogin = () => {};

  // if (isLoading) {
  //   return <Loading />;
  // }

  return <></>;
};
