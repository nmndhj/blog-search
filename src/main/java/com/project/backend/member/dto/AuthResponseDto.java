package com.project.backend.member.dto;


import lombok.Getter;

@Getter
public class AuthResponseDto {
    private String id;
    private String accessToken;

    public AuthResponseDto(String id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }
}
