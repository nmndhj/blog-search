import React, { useEffect } from "react";
import Avatar from '@mui/material/Avatar';
import CssBaseline from '@mui/material/CssBaseline';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Copyright from './Copyright';
import Pagination from "@mui/material/Pagination";
import Button from '@mui/material/Button';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import MenuList from './MenuList';
import ReactDOM from "react-dom";
import { getBlogInfo, getPageCnt, updateKeyword} from "../api/blog.api";
import { Bookmark, Keyword } from "../interfaces/Blog";
import { useAddBookmark, useAddKeyword } from "../hooks/blog.hook";

export const Main: React.FC = () => {
 
  const [sort, setSort] = React.useState("accuracy");
  const [query, setQuery] = React.useState("");
  const [page, setPage] = React.useState(1);
  const [totalP, setTotalP] = React.useState(0);
  const addKey = useAddKeyword();
  const addBookmark = useAddBookmark();


  //input 이벤트 발생시 변수 값 세팅
  const onChangeAccount = (e) => {
    setQuery(e.target.value);
  };

  //결과 테이블 랜더링
  function goRender(data){

    const element2 = 
    (data.data.map((row, index) => (
      <TableRow key={index+1} id={index+1}>
        <TableCell align="left">{index+1}</TableCell>
        <TableCell align="left">{row.blogName}</TableCell>
        <TableCell align="left"><div dangerouslySetInnerHTML={ {__html: row.title} }></div></TableCell>
        <TableCell><a href={row.blogUrl} target='_blank' rel='noreferrer'>{row.blogUrl}</a></TableCell>
        <TableCell align="left">{row.portalType}</TableCell>
        <TableCell align="left"><Button onClick={() => addBookmarkEvent(index+1)} variant="contained" color="success" >추가</Button></TableCell>
      </TableRow>
    )));

    ReactDOM.render(
      element2,
      document.getElementById('blogList')
    );

  }
  
  //검색버튼 클릭시 
  function goSearch(){
    //페이지 초기화 
    setPage(1);
    if(query==''){
      alert('검색어를 입력하세요.');
      return;
    }

    getPageCnt(sort, query).then(data => setTotalP(data));
    getBlogInfo(sort, query, "1").then(data => goRender({data}));
    
    //검색 키워드 추가 
    const keyword : Keyword = { keyword:query};
    addKey.mutate(keyword);
  }

  //즐겨찾기 추가 버튼 클릭
  function addBookmarkEvent(id){
    const blogName = document.getElementById(id).children[1].textContent;
    const blogUrl = document.getElementById(id).children[3].textContent;
    const portalType = document.getElementById(id).children[4].textContent;

    const bookmark : Bookmark = {blogName, blogUrl, portalType}; 
    addBookmark.mutate(bookmark);
  }

  //셀렉트박스 클릭시 
  const handleSelectChange = (event: SelectChangeEvent) => {

    if(query==''){
      alert('검색어를 입력하세요.');
      return;
    }
    //정렬 변수 세팅 
    setSort(event.target.value);
    getBlogInfo(event.target.value, query, ''+page).then(data => goRender({data}));
    
  };

  const handlePageChange = (event: React.ChangeEvent<unknown>, page: number) => {
    //페이지세팅
    if(query==''){
      alert('검색어를 입력하세요.');
      return;
    }

    setPage(page); 
    //페이지 기준 데이터 조회 
    getBlogInfo(sort, query, ''+page).then(data => goRender({data})); 
  };
  
  return (
    
    <Container component='main' maxWidth='xl'>
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
          <HomeOutlinedIcon />
        </Avatar>
        <Typography component='h1' variant='h5'>
          블로그 검색 페이지 
        </Typography>
        <Container maxWidth='lg' sx={{ mt: 4, mb: 4 }}>
          <Stack spacing={3} direction="row">
              <FormControl sx={{ m: 1, minWidth: 120 }} size="small">
                <InputLabel id="sortId">정렬</InputLabel>
                  <Select
                    labelId="demo-select-small"
                    id="demo-select-small"
                    value={sort}
                    onChange={handleSelectChange}
                  >
                    <MenuItem value={'accuracy'}>정확도순</MenuItem>
                    <MenuItem value={'recency'}>최신순</MenuItem>
                  </Select>
              </FormControl>
              <Box sx={{width: 500,maxWidth: '100%',}}>
                <TextField fullWidth label="검색어를 입력하세요." id="query" onChange={onChangeAccount}/>
              </Box>
              <Button variant="contained" onClick={() => goSearch()}>검색</Button>
            </Stack>
          <Grid item xs={20}>
            <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
              <Table sx={{ minWidth: 1000 }} size='medium'>
                <TableHead>
                  <TableRow>
                    <TableCell>No</TableCell>
                    <TableCell>블로그명</TableCell>
                    <TableCell>글 제목</TableCell>
                    <TableCell>블로그 url</TableCell>
                    <TableCell>포털 출처</TableCell>
                    <TableCell>즐겨찾기 추가</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody id='blogList'>
                </TableBody>                                
              </Table>
              <Stack spacing={2} id='pageList'>
                <Pagination count={totalP} page={page} onChange={handlePageChange} />
              </Stack>
            </Paper>
          </Grid>
        </Container>
      </Box>
      <MenuList></MenuList>
      <Copyright sx={{ mt: 8, mb: 4 }} />
    </Container>
  );
};

export default Main;