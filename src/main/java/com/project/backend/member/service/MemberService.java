package com.project.backend.member.service;

import com.project.backend.common.error.BizException;
import com.project.backend.common.error.ErrorCode;
import com.project.backend.member.dto.MemberDto;
import com.project.backend.member.entity.Member;
import com.project.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 회원가입
     * @param memberDto
     */
    @Transactional
    public void addMember(MemberDto memberDto){

        //기 가입된 멤버일 경우, Exception 처리
        if(memberRepository.existsById(memberDto.getId())){
            throw new BizException(ErrorCode.MEMBER_IS_EXIST);
        }

        //비밀번호 암호화
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));

        //DB저장
        Member member = modelMapper.map(memberDto, Member.class);
        memberRepository.save(member);
    }
}
