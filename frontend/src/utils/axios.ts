import axios, { AxiosError, AxiosResponse } from 'axios';
import history from './history';
import store from '../store';
import { Token } from '../interfaces/Auth';
import { setToken, initToken } from '../slices/token.slice';

const { dispatch } = store;

export const request = axios.create({
  baseURL: `${process.env.API_URL}`,
});

request.interceptors.request.use(function (config) {

//  console.log(config.url);
  if (config.url.indexOf('signup') < 0 && config.url !== '/login') {

    config.headers.Authorization = 'Bearer ' + localStorage.getItem('auth-token');
    
  } else {
    config.headers = null;
  }

  return config;
});

const onFulfilled = (response: AxiosResponse) => response;
const onRejected = async (error: AxiosError) => {
  if (!error.response) return error;

  if (error.response.status === 401) {
    localStorage.clear();
    alert('로그인이 필요합니다.');
    
  }
  else {
    return Promise.reject(error);
  }
};

const clearTokenAndGoLogin = () => {
  dispatch(initToken());
  localStorage.removeItem('auth-token');
  history.push('/login');
};

request.interceptors.response.use(onFulfilled, onRejected);
