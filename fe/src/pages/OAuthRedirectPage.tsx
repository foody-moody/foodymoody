import { useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';

export const OAuthRedirectPage = () => {
  const [params] = useSearchParams();
  const code = params.get('code');
  console.log('code', code);

  //mutate작성

  useEffect(() => {
    if (code) {
      //mutate실행
    }
  }, [code]);

  // const handleOauthLogin = () => {};

  // if (isLoading) {
  //   return <Loading />;
  // }

  return <></>;
};
