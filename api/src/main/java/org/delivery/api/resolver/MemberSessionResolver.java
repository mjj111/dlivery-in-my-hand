package org.delivery.api.resolver;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.MemberSession;
import org.delivery.api.domain.member.model.Member;
import org.delivery.api.domain.member.service.MemberService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class MemberSessionResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        var annotation = parameter.hasParameterAnnotation(MemberSession.class);
        var parameterType = parameter.getParameterType().equals(Member.class);
        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // support parameter 에서 true 반환시 여기 실행
        // request context holder에서 찾아오기
        var requestContext = RequestContextHolder.getRequestAttributes();
        var memberId = requestContext.getAttribute("memberId", RequestAttributes.SCOPE_REQUEST);

        var memberEntity = memberService.getMemberWithThrow(Long.parseLong(memberId.toString()));

        return Member.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .email(memberEntity.getEmail())
                .status(memberEntity.getStatus())
                .password(memberEntity.getPassword())
                .address(memberEntity.getAddress())
                .registeredAt(memberEntity.getRegisteredAt())
                .unregisteredAt(memberEntity.getUnregisteredAt())
                .lastLoginAt(memberEntity.getLastLoginAt())
                .build()
                ;
    }
}