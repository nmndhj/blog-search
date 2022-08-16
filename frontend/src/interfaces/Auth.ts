export interface User {
  id: string;
  password: string;
}

export interface Token {
  accessToken: string;
}

export interface Login {
  isLogin: boolean;
  isLoading: boolean;
}

export interface Cust {
  id?: string;
  password?: string;
  custName?: string;
  svcNum?: string;
  authLevel?: string;
  regDate?: string;
  updDate?: string;
  regId?: string;
  updId?: string;
}

export interface Member {
  id?: string;
  password?: string;
  name?: string;
  email?: string;
}