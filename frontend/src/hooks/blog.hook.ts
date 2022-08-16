import { Sort } from '@mui/icons-material';
import { useMutation, useQuery } from 'react-query';
import { useNavigate } from 'react-router';
import { getBookmarkInfo, getKeywordInfo, getBlogInfo, updateKeyword, addBookmark, deleteBookmark } from '../api/blog.api';
import { Bookmark, Keyword } from '../interfaces/Blog';

export enum blogEnum {
    Bookmark = 'bookmark',
    Keyword = 'keyword',
    BlogInfo = 'bloginfo',
    Search = 'seac'
  }

export const useGetBookmark = () =>
  useQuery(blogEnum.Bookmark, getBookmarkInfo, {
    staleTime: 5000,
  });
  
export const useGetKeyword = () =>
  useQuery(blogEnum.Keyword, getKeywordInfo, {
    staleTime: 5000,
  });

//키워드 추가
export const useAddKeyword = () => {

  return useMutation((keyword: Keyword) => (updateKeyword(keyword)), {
    onSuccess: (data, variables, context) => {
      console.log('검색어 업데이트 성공');
    },
    onError: (error) => {
      alert('검색어 업데이트  실패');
    },
    onSettled: () => {},
  });
};

//북마크 추가 
export const useAddBookmark = () => {
  return useMutation((bookmark: Bookmark) => (addBookmark(bookmark)), {
    onSuccess: (data, variables, context) => {
      console.log('북마크 추가 성공');

      if(data.data.errorCode=='BIZ003'){
        alert('이미 즐겨찾기에 추가되어 있습니다.')
      }else{
        alert('즐겨찾기에 추가되었습니다.');
      }
    },
    onError: (error) => {
      alert('북마크 추가 실패');
    },
    onSettled: () => {},
  });
}

//북마크 삭제
export const useDeleteBookmark = () => {
  return useMutation((blogUrl : String) => (deleteBookmark(blogUrl)),{
    onSuccess: (data, variables, context) => {
      alert('삭제되었습니다.');
    },
    onError: (error) => {
      alert('북마크 삭제 실패');
    },
    onSettled: () => {},
    
  });
}

