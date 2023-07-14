package org.delivery.api.domain.member.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.member.controller.model.MemberLoginRequest;
import org.delivery.api.domain.member.model.Member;
import org.delivery.api.domain.member.controller.model.MemberRegisterRequest;
import org.delivery.api.domain.member.controller.model.MemberResponse;
import org.delivery.api.domain.member.converter.MemberConverter;
import org.delivery.api.domain.member.service.MemberService;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;


@RequiredArgsConstructor
@Business
@Slf4j
public class MemberBusiness {

    private final MemberService memberService;
    private final MemberConverter memberConverter;
    private final TokenBusiness tokenBusiness;


    public MemberResponse register(MemberRegisterRequest request) {

        var entity = memberConverter.toEntity(request);
        var newEntity = memberService.register(entity);
        var response = memberConverter.toResponse(newEntity);
        return response;

    }

    public MemberResponse me(
            Long memberId
    ) {
        log.info(String.valueOf(memberId));
        var userEntity = memberService.getMemberWithThrow(memberId);
        var response = memberConverter.toResponse(userEntity);
        return response;
    }

    public TokenResponse login(MemberLoginRequest request) {
        var userEntity = memberService.login(request.getEmail(), request.getPassword());
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

}