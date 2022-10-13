package com.lion.spring_paymodule.app.member.service;


import com.lion.spring_paymodule.app.member.entity.Member;
import com.lion.spring_paymodule.app.member.repository.MemberRepository;
import com.lion.spring_paymodule.app.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final JwtProvider jwtProvider;

    public Member join(String username, String password, String email) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public String generateAccessToken(Member member) {
        String accessToken = member.getAccessToken();

        if (StringUtils.hasLength(accessToken) == false ) {
            accessToken = jwtProvider.generateAccessToken(member.getAccessTokenClaims(), (60L * 60 * 24 * 365 * 100));
            member.setAccessToken(accessToken);
        }

        return accessToken;
    }

    public boolean verifyWithWhiteList(Member member, String token) {
        return member.getAccessToken().equals(token);
    }
}
