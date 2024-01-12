import { RouterProvider } from 'react-router-dom';
import { useRefreshToken } from 'service/queries/auth';
import router from 'routes/router';

export const RouteContainer = () => {
  useRefreshToken();
  return <RouterProvider router={router} />;
};
