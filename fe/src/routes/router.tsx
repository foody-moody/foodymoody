import { createBrowserRouter } from 'react-router-dom';
import { CollectionPage } from 'pages/CollectionPage';
import { DetailFeedModalPage } from 'pages/DetailFeedPage';
import { ErrorPage } from 'pages/ErrorPage';
import { HomePage } from 'pages/HomePage';
import { Layout } from 'pages/Layout';
import { LoginPage } from 'pages/LoginPage';
import { NewFeedPage } from 'pages/NewFeedPage';
import { NotiPage } from 'pages/NotiPage';
import { ProfilePage } from 'pages/ProfilePage';
import { ProtectedRoute } from 'pages/ProtectedRoute';
import { RegisterPage } from 'pages/RegisterPage';
import { SearchPage } from 'pages/SearchPage';
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
                path: PATH.NEW_FEED /* TODO. 비로그인 유저 관련 처리 */,
                element: <NewFeedPage />,
              },
            ],
          },
          {
            element: <ProtectedRoute />,
            children: [
              {
                path: PATH.NOTI,
                element: <NotiPage />,
              },
            ],
          },
          {
            path: PATH.PROFILE,
            element: <ProfilePage />,
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
