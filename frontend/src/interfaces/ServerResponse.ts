export interface ServerResponse<T> {
  data: Array<T>;
  errorCode: string;
}
