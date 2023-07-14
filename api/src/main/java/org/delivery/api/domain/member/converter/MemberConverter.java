package org.delivery.api.domain.member.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.member.controller.model.MemberRegisterRequest;
import org.delivery.api.domain.member.controller.model.MemberResponse;
import org.delivery.db.member.MemberEntity;


import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class MemberConverter {

    public MemberEntity toEntity(MemberRegisterRequest request){

        return Optional.ofNullable(request)
                .map(it ->{
                    // to entity
                    return MemberEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "MemberRegisterRequest Null"));
    }

    public MemberResponse toResponse(MemberEntity memberEntity) {

        return Optional.ofNullable(memberEntity)
                .map(it ->{
                    // to response
                    return MemberResponse.builder()
                            .id(memberEntity.getId())
                            .name(memberEntity.getName())
                            .status(memberEntity.getStatus())
                            .email(memberEntity.getEmail())
                            .address(memberEntity.getAddress())
                            .registeredAt(memberEntity.getRegisteredAt())
                            .unregisteredAt(memberEntity.getUnregisteredAt())
                            .lastLoginAt(memberEntity.getLastLoginAt())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "MemberEntity Null"));

    }
}