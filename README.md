###블로그 검색 어플리케이션 
- jar 다운로드 링크 : https://github.com/nmndhj/blog-search/blob/master/blog-app.jar 
- 실행방법 :
    ```shell
    java -jar blog-app.jar 
    ```
- 어플리케이션 Home : http://localhost:8081/

- 최초 로그인 계정(id,password) : user1 / test 


####Frontend : React

[화면]
- 로그인
- 회원가입 
- 즐겨찾기 리스트 
- 블로그 검색
- 블로그 인기 검색어 Top 10 

####Backend : Spring boot 

[api 리스트]

- 로그인 인증 : /login
- 사용자 추가 : /signup
- 카카오 블로그 검색 조회 : /blog/kakao
- 카카오 블로그 페이지 수 조회 : /blog/kakao/pages
- 네이버 블로그 검색 API 조회 : /blog/naver
- 네이버 블로그 검색 페이지 수 조회 : /blog/naver/pages
- 블로그 즐겨찾기 조회,추가,삭제 : /bookmark 


