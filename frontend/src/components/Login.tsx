import * as React from 'react';
import { Link as RouterLink } from 'react-router-dom';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Copyright from './Copyright';
import { useLogin } from '../hooks/auth.hook';
import { User } from '../interfaces/Auth';

export const Login: React.FC = () => {

  const login = useLogin();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    if((data.get('id')=='')||(data.get('password')=='')){
      alert('id와 password를 입력하세요.');
      return;
    }

    const user: User = { id: data.get('id').toString(), password: data.get('password').toString() };
    login.mutate(user);
  };

  return (
    <Container component='main' maxWidth='xs'>
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
          <LockOutlinedIcon />
        </Avatar>
        <Typography component='h1' variant='h5'>
          로그인
        </Typography>
        <Box component='form' onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin='normal'
            required
            fullWidth
            id='id'
            label='아이디를 입력하세요'
            name='id'
            autoFocus
          />
          <TextField
            margin='normal'
            required
            fullWidth
            name='password'
            label='패스워드를 입력하세요'
            type='password'
            id='password'
          />
          <Button type='submit' fullWidth variant='contained' sx={{ mt: 3, mb: 2 }}>
            로그인
          </Button>
          <Grid container justifyContent='flex-end'>
            <Grid item>
              <Link component={RouterLink} to='/join' variant='body2'>
                {'회원가입'}
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
      <Copyright sx={{ mt: 8, mb: 4 }} />
    </Container>
  );
};

export default Login;
