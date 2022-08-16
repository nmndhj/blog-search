package com.project.backend.auth.service;

import com.project.backend.common.error.BizException;
import com.project.backend.common.error.ErrorCode;
import com.project.backend.member.entity.Member;
import com.project.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        return memberRepository.findById(userId)
                .map(this::createUserDetails)
                .orElseThrow(() -> new BizException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private UserDetails createUserDetails(Member member) {
        return new User(member.getId(), member.getPassword(), new ArrayList<>());
    }

}
