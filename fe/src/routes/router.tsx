import { createBrowserRouter } from 'react-router-dom';
import { HomePage } from 'pages/HomePage';
import { PATH } from 'constants/path';

const router = createBrowserRouter([
  {
    path: PATH.HOME,
    element: <HomePage />,
    children: [],
  },
]);

export default router;
