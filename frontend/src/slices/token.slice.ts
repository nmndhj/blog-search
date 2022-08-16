import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { Token } from '../interfaces/Auth';

const initialState = {
  accessToken: null,

} as Token;

export const tokenSlice = createSlice({
  name: 'auth',
  initialState: initialState,
  reducers: {
    setAccessToken: (state, { payload }: PayloadAction<string>) => {
      state.accessToken = payload;
    },
    setToken: (state, { payload }: PayloadAction<Token>) => {
      state.accessToken = payload.accessToken;
    },
    initToken: (state) => {
      state.accessToken = null;
    },
  },
});

export const { setAccessToken, setToken, initToken } = tokenSlice.actions;
export default tokenSlice.reducer;
