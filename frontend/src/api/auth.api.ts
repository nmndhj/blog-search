import { request } from '../utils/axios';
import { User, Cust, Member } from '../interfaces/Auth';
import { ServerResponse } from '../interfaces/ServerResponse';

export const token = async (user: User) => {
  return request.post('/login', user);
};

export const getCustInfo = async (): Promise<ServerResponse<Cust>> => {
  const { data } = await request.get('/bookmark?memberId=user1');
  return data;
};

export const setCustInfo = async (member: Member) => {
  return request.post('/signup', member);
};
