package org.delivery.api.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.member.business.MemberBusiness;
import org.delivery.api.domain.member.controller.model.MemberResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberBusiness memberBusiness;

    @GetMapping("/me")
    public Api<MemberResponse> me(){
        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());

        var memberId = requestContext.getAttribute("memberId", RequestAttributes.SCOPE_REQUEST);
        var response =  memberBusiness.me((Long) memberId);
        return Api.OK(response);
    }
}