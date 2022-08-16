package com.project.backend.member.controller;

import com.project.backend.member.dto.AuthRequestDto;
import com.project.backend.member.dto.AuthResponseDto;
import com.project.backend.member.dto.MemberDto;
import com.project.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //사용자 추가 API
    @PostMapping("/signup")
    public ResponseEntity<Object> addMember(@RequestBody MemberDto memberDto){
        memberService.addMember(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //로그인 인증 API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto requestDto) {
        return ResponseEntity.ok(memberService.login(requestDto));
    }
}
