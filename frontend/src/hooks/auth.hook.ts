import { useNavigate } from 'react-router-dom';
import { useAppDispatch } from '../store';
import { useMutation, useQuery } from 'react-query';
import { token, getCustInfo, setCustInfo } from '../api/auth.api';
import { User, Member } from '../interfaces/Auth';
import { setToken } from '../slices/token.slice';
import { setLoginSuccess } from '../slices/login.slice';

export enum AuthKeysEnum {
  Login = 'login',
  Cust = 'cust',
  Member = 'member'
}

export const useLogin = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  return useMutation((user: User) => token(user), {
    onSuccess: (data, variables, context) => {
      const { data: result } = data;
        
      if (!result || result.errorCode === 'UNAUTHORIZED_MEMBER') {
        alert('로그인 실패');
      } else {

        localStorage.setItem('auth-token', result.accessToken);
        localStorage.setItem('memberId', result.id);
        
        dispatch(setLoginSuccess());
        navigate('/bookmark');
      }
    },
    onError: (error) => {
      alert('로그인 실패');
    },
    onSettled: () => {},
  });
};

export const useGetCust = () =>
  useQuery(AuthKeysEnum.Cust, getCustInfo, {
    staleTime: 5000,
  });

export const useSetCust = () => {
  const navigate = useNavigate();

  return useMutation((member: Member) => setCustInfo(member), {
    onSuccess: (data, variables, context) => {
      alert('회원가입 성공. 로그인하세요');
      navigate('/login');
    },
    onError: (error) => {
      alert('회원가입 실패');
    },
    onSettled: () => {},
  });
};
