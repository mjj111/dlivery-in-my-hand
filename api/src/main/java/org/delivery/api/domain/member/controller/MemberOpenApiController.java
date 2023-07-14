package org.delivery.api.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.member.business.MemberBusiness;
import org.delivery.api.domain.member.controller.model.MemberLoginRequest;
import org.delivery.api.domain.member.controller.model.MemberRegisterRequest;
import org.delivery.api.domain.member.controller.model.MemberResponse;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class MemberOpenApiController {

    private final MemberBusiness userBusiness;


    @PostMapping("/register")
    public Api<MemberResponse> register(
            @Valid
            @RequestBody Api<MemberRegisterRequest> request
    ){
        var response = userBusiness.register(request.getBody());
        return Api.OK(response);
    }


    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody Api<MemberLoginRequest> request
    ){
        var response = userBusiness.login(request.getBody());
        return Api.OK(response);
    }


}