package com.project.backend.auth.controller;

import com.project.backend.auth.dto.AuthRequestDto;
import com.project.backend.auth.dto.AuthResponseDto;
import com.project.backend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController : JWT 인증 관련 Rest Controller
 * @author  ihyejin
 * @Version 1.0.0
 * @Date 2022/08/15
 * @Description :
 * ===========================================================================
 * DATE         AUTHOR          NOTE
 -----------------------------------------------------------------------------
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthService authService;

    //로그인 인증 API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }

}
