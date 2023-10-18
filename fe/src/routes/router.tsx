import { createBrowserRouter } from 'react-router-dom';
import { PATH } from 'constants/path';

import { HomePage } from 'pages/HomePage';
import { TestPage } from 'pages/test/TestPage';

const router = createBrowserRouter([
  {
    path: PATH.HOME,
    element: <HomePage />,
    children: [],
  },
  {
    path: PATH.TEST,
    element: <TestPage />,
    children: [],
  },
]);

export default router;
