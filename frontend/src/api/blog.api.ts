import axios from 'axios';
import { Bookmark, BookmarkDelete, Keyword } from '../interfaces/Blog';
import { request } from '../utils/axios';

//사용자별 북마크 조회 api 
export async function getBookmarkInfo() {
    const id = localStorage.getItem("memberId");
    const response = await request.get(
      `/bookmark?memberId=` + id
    );
    return response.data;
  }

//키워드 Top 10 조회 api 
export async function getKeywordInfo() {
  const response = await request.get(
    `/keyword`
  );

  return response.data;
}

//블로그 검색 결과 조회 api 
export async function getBlogInfo(sort:String, query:String, page:String) {
  const response = await request.get(`/blog/kakao?query=` + query + `&sort=` + sort + `&page=` + page + `&size=10`);
  return response.data;
}

//블로그 검색 데이터 페이징 수 조회 api
export async function getPageCnt(sort:String, query:String){
  const response = await request.get(`/blog/kakao/pages?query=` + query + `&sort=` + sort + `&size=10`);
  return response.data;
}

//검색어 횟수 업데이트 
export async function updateKeyword(keyword : Keyword){
  await request.post('/keyword',keyword);
}

//북마크 추가 
export async function addBookmark(bookmark : Bookmark){
  const id = localStorage.getItem("memberId");
  return await request.post('/bookmark?memberId='+id,bookmark); 
}

//북마크 삭제 
export async function deleteBookmark(blogUrl:String){
  const id = localStorage.getItem("memberId");
  return await request.delete(`/bookmark?memberId=`+id+`&blogUrl=`+blogUrl);
}
