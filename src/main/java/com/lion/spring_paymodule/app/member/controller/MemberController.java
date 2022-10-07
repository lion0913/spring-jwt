package com.lion.spring_paymodule.app.member.controller;

import com.lion.spring_paymodule.app.base.dto.ResultData;
import com.lion.spring_paymodule.app.member.dto.LoginDto;
import com.lion.spring_paymodule.app.member.entity.Member;
import com.lion.spring_paymodule.app.member.service.MemberService;
import com.lion.spring_paymodule.app.security.MemberContext;
import com.lion.spring_paymodule.app.util.JwtProvider;
import com.lion.spring_paymodule.app.util.Util;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberContext memberContext) {
        return "안녕" + memberContext;
    }

    @GetMapping("/me")
    public ResponseEntity<ResultData> me(@AuthenticationPrincipal MemberContext memberContext) {
        if(memberContext == null) {
            return Util.spring.responseEntityOf(ResultData.failOf(null));
        }

        return Util.spring.responseEntityOf(ResultData.successOf(memberContext));
    }

    @PostMapping("/login")
    public ResponseEntity<ResultData> login(@Valid @RequestBody LoginDto loginDto) {

        Member member = memberService.findByUsername(loginDto.getUsername()).orElse(null);
        if (member == null) {
            return Util.spring.responseEntityOf(ResultData.of("F-2", "일치하는 회원이 존재하지 않습니다."));
        }

        if (passwordEncoder.matches(loginDto.getPassword(), member.getPassword()) == false) {
            return Util.spring.responseEntityOf(ResultData.of("F-3", "비밀번호가 일치하지 않습니다."));
        }

        String accessToken = memberService.generateAccessToken(member);

        return Util.spring.responseEntityOf(
                ResultData.of(
                        "S-1",
                        "로그인 성공, Access Token을 발급합니다.",
                        Util.mapOf(
                                "accessToken", accessToken
                        )
                ),
                Util.spring.httpHeadersOf("Authentication", accessToken)
        );
    }

}
