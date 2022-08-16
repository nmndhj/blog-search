import React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import Avatar from '@mui/material/Avatar';
import TextField from '@mui/material/TextField';
import CssBaseline from '@mui/material/CssBaseline';
import Button from '@mui/material/Button';
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
import { useGetKeyword } from '../hooks/blog.hook';
import MenuList from './MenuList';

export const Keyword: React.FC = () => {
  const { isLoading, isError, data: keywords } = useGetKeyword();

  if (isLoading) {
    return <span>Loading...</span>;
  }

  if (isError) {
    return <span>Error: </span>;
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
            블로그 인기 검색어 Top 10
        </Typography>
        <Container maxWidth='lg' sx={{ mt: 3, mb: 3 }}>
          <Grid item xs={12}>
            <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
              <Table size='small'>
                <TableHead>
                  <TableRow>
                    <TableCell>순위</TableCell>
                    <TableCell>검색어</TableCell>
                    <TableCell>검색된 횟수</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {keywords.map((row, index) => (
                    <TableRow key={row.keyName}>
                      <TableCell>{index+1}</TableCell>
                      <TableCell>{row.keyName}</TableCell>
                      <TableCell>{row.searchCount}</TableCell>
                    </TableRow>
                  ))}
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

export default Keyword;
