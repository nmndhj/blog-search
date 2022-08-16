package com.project.backend.member.dto;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class AuthRequestDto {

    private String id;              //멤버아이디
    private String password;        //패스워드

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(id, password);
    }

}