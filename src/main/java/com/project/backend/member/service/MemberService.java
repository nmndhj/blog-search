package com.project.backend.member.service;

import com.project.backend.member.dto.AuthRequestDto;
import com.project.backend.member.dto.AuthResponseDto;
import com.project.backend.member.util.JwtTokenProvider;
import com.project.backend.common.error.BizException;
import com.project.backend.common.error.ErrorCode;
import com.project.backend.member.dto.MemberDto;
import com.project.backend.member.entity.Member;
import com.project.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
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

    private final AuthenticationManagerBuilder managerBuilder;

    private final JwtTokenProvider tokenProvider;

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

    /**
     * 로그인 및 토큰 생성
     */
    public AuthResponseDto login(AuthRequestDto authRequestDto){

        //존재하지 않은 id 일 경우, Exception
        Member member = memberRepository.findById(authRequestDto.getId()).orElseThrow(() -> new BizException(ErrorCode.LOGIN_FAILED));

        //Password 미일치, Exception
        if(!encoder.matches(authRequestDto.getPassword(), member.getPassword())) throw new BizException(ErrorCode.LOGIN_FAILED);

        //id,pwd로 인증정보 생성
        UsernamePasswordAuthenticationToken authenticationToken = authRequestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }
}
