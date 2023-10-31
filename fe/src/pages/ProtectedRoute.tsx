import { Navigate, Outlet, useLocation } from 'react-router-dom';
import { useAuthState } from 'hooks/auth/useAuth';
import { PATH } from 'constants/path';

export const ProtectedRoute = () => {
  const { isLogin } = useAuthState();
  const currentPath = useLocation();

  return isLogin ? (
    <Outlet />
  ) : (
    <Navigate to={PATH.LOGIN} replace state={{ redirectedFrom: currentPath }} />
  );
};
