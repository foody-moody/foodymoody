import { createBrowserRouter } from 'react-router-dom';
import { AccountPage } from 'pages/AcountPage';
import { CollectionPage } from 'pages/CollectionPage';
import { DetailFeedModalPage } from 'pages/DetailFeedPage';
import { ErrorPage } from 'pages/ErrorPage';
import { FollowersModalPage } from 'pages/FollowersPage';
import { FollowingsModalPage } from 'pages/FollowingsPage';
import { HomePage } from 'pages/HomePage';
import { Layout } from 'pages/Layout';
import { LoginPage } from 'pages/LoginPage';
import { NewFeedModalPage } from 'pages/NewFeedPage';
import { NotiPage } from 'pages/NotiPage';
import { PasswordPage } from 'pages/PasswordPage';
import { ProfileEditPage } from 'pages/ProfileEditPage';
import { ProfilePage } from 'pages/ProfilePage';
import { ProtectedRoute } from 'pages/ProtectedRoute';
import { RegisterPage } from 'pages/RegisterPage';
import { SearchPage } from 'pages/SearchPage';
import { SettingPage } from 'pages/SettingPage';
import { PATH } from 'constants/path';

const router = createBrowserRouter([
  {
    path: PATH.HOME,
    element: <Layout />,
    errorElement: <ErrorPage />,
    children: [
      {
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
                  // 다른 페이지 모달들도 추가할 필요가 있을지?
                ],
              },
              {
                path: PATH.PROFILE,
                element: <ProfilePage />,
                children: [
                  {
                    path: PATH.PROFILE + PATH.FOLLOW + '/:id',
                    element: <FollowingsModalPage />,
                  },
                  {
                    path: PATH.PROFILE + PATH.FOLLOW + '/:id',
                    element: <FollowersModalPage />,
                  },
                ],
              },
              {
                path: PATH.PROFILE + '/:id',
                element: <ProfilePage />,
              },
              {
                path: PATH.PROFILE_EDIT,
                element: <ProfileEditPage />,
              },
              {
                path: PATH.SETTING,
                element: <SettingPage />,
              },
              {
                path: PATH.PASSWORD,
                element: <PasswordPage />,
              },
              {
                path: PATH.ACCOUNT,
                element: <AccountPage />,
              },
            ],
          },
          {
            path: PATH.COLLECTION,
            element: <CollectionPage />,
          },
          {
            path: PATH.SEARCH,
            element: <SearchPage />,
          },
        ],
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
]);

export default router;
