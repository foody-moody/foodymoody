import loadable from '@loadable/component';
import { createBrowserRouter } from 'react-router-dom';
import { AccountSettingPage } from 'pages/AccountSettingPage';
import { CollectionDetailPage } from 'pages/CollectionDetailPage';
import { CollectionPage } from 'pages/CollectionPage';
// import { DetailFeedModalPage } from 'pages/DetailFeedPage';
import { ErrorPage } from 'pages/ErrorPage';
import { FollowModalPage } from 'pages/FollowPage';
import { HomePage } from 'pages/HomePage';
import { Layout } from 'pages/Layout';
import { LoginPage } from 'pages/LoginPage';
import { NewFeedModalPage } from 'pages/NewFeedPage';
import { NotiPage } from 'pages/NotiPage';
import { NotiSettingPage } from 'pages/NotiSettingPage';
import { OAuthRedirectPage } from 'pages/OAuthRedirectPage';
import { PasswordPage } from 'pages/PasswordPage';
import { ProfileEditPage } from 'pages/ProfileEditPage';
import { ProfilePage } from 'pages/ProfilePage';
import { ProtectedRoute } from 'pages/ProtectedRoute';
import { RegisterPage } from 'pages/RegisterPage';
import { SearchPage } from 'pages/SearchPage';
import { SettingPage } from 'pages/SettingPage';
import { StorePage } from 'pages/StorePage';
import { Spinner } from 'components/common/loading/spinner';
import { PATH } from 'constants/path';

const SpinnerPage = () => {
  return (
    <div style={{ width: '100%', height: '100%', background: 'red' }}>
      <Spinner isLoading />
    </div>
  );
};

const DetailFeedModalPage = loadable(
  () =>
    import('pages/DetailFeedPage').then((module) => module.DetailFeedModalPage),
  {
    fallback: <SpinnerPage />,
  }
);

const router = createBrowserRouter([
  {
    path: PATH.HOME,
    element: <Layout />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: '/',
        element: <HomePage />,
        children: [
          {
            path: PATH.DETAIL_FEED + '/:id',
            element: <DetailFeedModalPage />,
          },
          {
            path: PATH.NEW_FEED,
            element: <NewFeedModalPage />,
          },
          {
            path: PATH.EDIT_FEED + '/:id',
            element: <NewFeedModalPage />,
          },
        ],
      },
      {
        element: <ProtectedRoute />,
        children: [
          {
            path: PATH.NOTI,
            element: <NotiPage />,
            children: [
              {
                path: PATH.NOTI + PATH.DETAIL_FEED + '/:id',
                element: <DetailFeedModalPage />,
              },
            ],
          },
          {
            path: PATH.PROFILE,
            element: <ProfilePage />,
            children: [
              {
                path: PATH.PROFILE + ':id' + PATH.FOLLOWING,
                element: <FollowModalPage />,
              },
              {
                path: PATH.PROFILE + ':id' + PATH.FOLLOWER,
                element: <FollowModalPage />,
              },
              {
                path: PATH.PROFILE + PATH.DETAIL_FEED + '/:id',
                element: <DetailFeedModalPage />,
              },
            ],
          },
          {
            path: PATH.PROFILE + '/:id',
            element: <ProfilePage />,
          },
          {
            path: PATH.SETTING,
            element: <SettingPage />,
            children: [
              {
                path: PATH.SETTING_NOTI,
                element: <NotiSettingPage />,
              },
              {
                path: PATH.SETTING_PROFILE,
                element: <ProfileEditPage />,
              },
              {
                path: PATH.SETTING_ACCOUNT,
                element: <AccountSettingPage />,
              },
            ],
          },

          {
            path: PATH.PASSWORD,
            element: <PasswordPage />,
          },
        ],
      },
      {
        path: PATH.COLLECTION,
        element: <CollectionPage />,
      },
      {
        path: PATH.COLLECTION + '/:id',
        element: <CollectionDetailPage />,
      },
      {
        path: PATH.SEARCH,
        element: <SearchPage />,
      },
      {
        path: PATH.STORE + '/:id',
        element: <StorePage />,
        // loader,
      },
    ],
  },

  {
    path: PATH.LOGIN,
    element: <LoginPage />,
  },
  {
    path: PATH.REGISTER,
    element: <RegisterPage />,
  },
  {
    path: PATH.GOOGLE,
    element: <OAuthRedirectPage />,
  },
]);

export default router;
