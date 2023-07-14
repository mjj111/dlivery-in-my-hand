package org.delivery.api.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.MemberErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.member.MemberEntity;
import org.delivery.db.member.MemberRepository;
import org.delivery.db.member.enums.MemberStatus;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberEntity register(MemberEntity memberEntity){
        return Optional.ofNullable(memberEntity)
                .map(it ->{
                    memberEntity.setStatus(MemberStatus.REGISTERED);
                    memberEntity.setRegisteredAt(LocalDateTime.now());
                    return memberRepository.save(memberEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Member Entity Null"));
    }

    public MemberEntity login(
            String email,
            String password
    ){
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public MemberEntity getUserWithThrow(
            String email,
            String password
    ){
        return memberRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                email,
                password,
                MemberStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    public MemberEntity getMemberWithThrow(
            Long memberId
    ){
        return memberRepository.findFirstByIdAndStatusOrderByIdDesc(
                memberId,
                MemberStatus.REGISTERED
        ).orElseThrow(()-> new ApiException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}