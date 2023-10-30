import { createBrowserRouter } from 'react-router-dom';
import { CollectionPage } from 'pages/CollectionPage';
import { DetailFeedPage } from 'pages/DetailFeedPage';
import { ErrorPage } from 'pages/ErrorPage';
import { HomePage } from 'pages/HomePage';
import { Layout } from 'pages/Layout';
import { LoginPage } from 'pages/LoginPage';
import { NewFeedPage } from 'pages/NewFeedPage';
import { NotiPage } from 'pages/NotiPage';
import { ProfilePage } from 'pages/ProfilePage';
import { RegisterPage } from 'pages/RegisterPage';
import { SearchPage } from 'pages/SearchPage';
import { TestPage } from 'pages/test/TestPage';
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
            index: true,
            element: <HomePage />,
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
            path: PATH.NOTI,
            element: <NotiPage />,
          },
          {
            path: PATH.SEARCH,
            element: <SearchPage />,
          },
          {
            path: PATH.NEW_FEED,
            element: <NewFeedPage />,
          },
          {
            path: PATH.DETAIL_FEED,
            element: <DetailFeedPage />,
          },
        ],
      },
    ],
  },
  {
    path: PATH.TEST,
    element: <TestPage />,
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
