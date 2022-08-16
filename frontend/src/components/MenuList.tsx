import * as React from 'react';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import ButtonGroup from '@mui/material/ButtonGroup';
import { useNavigate } from 'react-router-dom';

export const MenuList: React.FC = () => {

    const navigate = useNavigate();
    
    function goRoute(value) {
        navigate(value);    
    }

    return(
    <Container>
      <ButtonGroup variant="outlined" aria-label="text button group">
          <Button onClick={() => goRoute("/keyword")}>블로그 인기 검색어 Top 10</Button>
          <Button onClick={() => goRoute("/")}>블로그 검색</Button>
          <Button onClick={() => goRoute("/bookmark")}>즐겨찾기 리스트</Button>
          <Button onClick={() => goRoute("/login")}>로그아웃</Button>
      </ButtonGroup>
    </Container>   
    )};

export default MenuList;