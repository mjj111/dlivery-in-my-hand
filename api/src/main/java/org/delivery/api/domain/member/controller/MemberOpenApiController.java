package org.delivery.api.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.member.business.MemberBusiness;
import org.delivery.api.domain.member.controller.model.MemberRegisterRequest;
import org.delivery.api.domain.member.controller.model.MemberResponse;
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


}