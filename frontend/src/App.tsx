import React from 'react';
import { Routes, Route, unstable_HistoryRouter as HistoryRouter } from 'react-router-dom';
import { QueryClientProvider, QueryClient } from 'react-query';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import history from './utils/history';
import Join from './components/Join';
import Login from './components/Login';
import Main from './components/Main';
import Bookmark from './components/Bookmark';
import Keyword from './components/Keyword';

import AuthRouter from './components/AuthRouter';

const queryClient = new QueryClient();
const theme = createTheme();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider theme={theme}>
        <HistoryRouter history={history}>
          <Routes>
            <Route path='/login' element={<Login />} />
            <Route path='/join' element={<Join />} />
            <Route element={<AuthRouter />}>
            <Route path='/bookmark' element={<Bookmark />} />
            <Route path='/' element={<Main />} />
            <Route path='/keyword' element={<Keyword />} />
            </Route>
          </Routes>
        </HistoryRouter>
      </ThemeProvider>
    </QueryClientProvider>
  );
}

export default App;
