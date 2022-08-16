import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { Login } from '../interfaces/Auth';

const initialState = {
  isLogin: false,
  isLoading: false,
} as Login;

export const loginSlice = createSlice({
  name: 'login',
  initialState: initialState,
  reducers: {
    setLogin: (state, { payload }: PayloadAction<boolean>) => {
      state.isLogin = payload;
    },
    setLoading: (state, { payload }: PayloadAction<boolean>) => {
      state.isLoading = payload;
    },
    setLoginSuccess: (state) => {
      state.isLogin = true;
      state.isLoading = true;
    },
    setLoginFail: (state) => {
      state.isLogin = false;
      state.isLoading = true;
    },
  },
});

export const { setLogin, setLoading, setLoginSuccess, setLoginFail } = loginSlice.actions;
export default loginSlice.reducer;
