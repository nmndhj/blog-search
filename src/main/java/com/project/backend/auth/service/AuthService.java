package com.project.backend.auth.service;

import com.project.backend.auth.dto.AuthRequestDto;
import com.project.backend.auth.dto.AuthResponseDto;
import com.project.backend.auth.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder managerBuilder;

    private final JwtTokenProvider tokenProvider;

    /**
     * 로그인 인증 (Access token 발급)
     * @param requestDto
     * @return AuthResponseDto
     */
    public AuthResponseDto login(AuthRequestDto requestDto) {

        //id,pwd로 인증정보 생성
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        //authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }
}
