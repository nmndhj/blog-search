package com.project.backend.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private String id;               //사용자 id

    private String name;            //사용자 이름

    private String email;           //이메일

    private String password;        //비밀번호

    @Builder
    public MemberDto(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
