import React from 'react';
import Avatar from '@mui/material/Avatar';
import CssBaseline from '@mui/material/CssBaseline';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Copyright from './Copyright';
import { useDeleteBookmark, useGetBookmark } from '../hooks/blog.hook';
import MenuList from './MenuList';
import { BookmarkDelete } from '../interfaces/Blog';
import { deleteBookmark, getBookmarkInfo } from '../api/blog.api';
import ReactDOM from 'react-dom';

export const Bookmark: React.FC = () => {
  //초기화 
  getBookmarkInfo().then(data => goRender({data}));
  //북마크 삭제
  const delBookmark = useDeleteBookmark();


  //결과 테이블 랜더링
  function goRender(data){
    const element = 
    (data.data.map((row, index) => (
      <TableRow id={index+1} key={index+1}>
        <TableCell>{row.blogName}</TableCell>
        <TableCell><a href={row.blogUrl} target='_blank' rel='noreferrer'>{row.blogUrl}</a></TableCell>
        <TableCell>{row.portalType}</TableCell>
        <TableCell><Button variant="contained" color="error" onClick={() => delBookmarkFunction(index+1)} >삭제</Button></TableCell>
      </TableRow>
    )));

    ReactDOM.render(
      element,
      document.getElementById('bookmarkList')
    );
  }

  //북마크 삭제 이벤트 
  function delBookmarkFunction(id){
    const blogUrl = document.getElementById(id).children[1].textContent;
    
    //북마크 삭제 
    delBookmark.mutate(blogUrl);
    //북마크 리스트 조회 
    getBookmarkInfo().then(data => goRender({data}));

  }
  
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
          즐겨찾기(북마크) 리스트
        </Typography>


        <Container maxWidth='lg' sx={{ mt: 4, mb: 4 }}>
          <Grid item xs={12}>
            <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
              <Table size='small'>
                <TableHead>
                  <TableRow>
                    <TableCell>블로그 명 </TableCell>
                    <TableCell>블로그 URL</TableCell>
                    <TableCell>검색 포털 </TableCell>
                    <TableCell>즐겨찾기 삭제</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody id='bookmarkList'>
                </TableBody>
              </Table>
            </Paper>
          </Grid>
          
        </Container>
      </Box>
      
      <MenuList></MenuList>
      <Copyright sx={{ mt: 8, mb: 4 }} />
    </Container>
  );
};

export default Bookmark;
