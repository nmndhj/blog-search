package com.project.backend.member.controller;

import com.project.backend.member.dto.MemberDto;
import com.project.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/signup")
public class MemberController {

    private final MemberService memberService;

    //사용자 추가 API
    @PostMapping
    public ResponseEntity<Object> addMember(@RequestBody MemberDto memberDto){
        memberService.addMember(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
