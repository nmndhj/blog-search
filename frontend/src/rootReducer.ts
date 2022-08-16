import { combineReducers } from '@reduxjs/toolkit';
import tokenSlice from './slices/token.slice';
import loginSlice from './slices/login.slice';

const reducer = combineReducers({
  tokenSlice,
  loginSlice,
});

export type ReducerType = ReturnType<typeof reducer>;
export default reducer;
