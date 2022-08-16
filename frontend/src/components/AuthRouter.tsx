import React, { useEffect } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { RootState, useAppSelector, useAppDispatch } from '../store';
import { setLoginSuccess, setLoginFail } from '../slices/login.slice';
// import { useReIssue } from '../hooks/auth.hook';

interface AuthRouterProps {
  redirectPath?: string;
}

export const AuthRouter: React.FC<AuthRouterProps> = ({ redirectPath = '/login' }) => {
  const { isLogin, isLoading } = useAppSelector((state: RootState) => state.loginSlice);
  const dispatch = useAppDispatch();

  useEffect(() => {
    const refreshToken = localStorage.getItem('rt');

    if (!isLogin) {
      dispatch(setLoginFail());
    }
  }, []);

  if (!isLoading) {
    return <div>Lodding...</div>;
  }

  if (!isLogin) {
    return <Navigate to={redirectPath} replace />;
  }

  return <Outlet />;
};

export default AuthRouter;
