package org.delivery.api.domain.member.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.member.model.Member;
import org.delivery.api.domain.member.controller.model.MemberRegisterRequest;
import org.delivery.api.domain.member.controller.model.MemberResponse;
import org.delivery.api.domain.member.converter.MemberConverter;
import org.delivery.api.domain.member.service.MemberService;


@RequiredArgsConstructor
@Business
public class MemberBusiness {

    private final MemberService memberService;
    private final MemberConverter memberConverter;


    public MemberResponse register(MemberRegisterRequest request) {

        var entity = memberConverter.toEntity(request);
        var newEntity = memberService.register(entity);
        var response = memberConverter.toResponse(newEntity);
        return response;

    }


    public MemberResponse me(
            Member member
    ) {
        var userEntity = memberService.getUserWithThrow(member.getEmail(),member.getPassword());
        var response = memberConverter.toResponse(userEntity);
        return response;
    }
}