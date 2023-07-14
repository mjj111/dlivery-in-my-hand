package org.delivery.api.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.MemberSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.member.business.MemberBusiness;
import org.delivery.api.domain.member.controller.model.MemberResponse;
import org.delivery.api.domain.member.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberBusiness userBusiness;

    @GetMapping("/me")
    public Api<MemberResponse> me(
            @MemberSession Member member
    ){
        var response = userBusiness.me(member);
        return Api.OK(response);
    }
}