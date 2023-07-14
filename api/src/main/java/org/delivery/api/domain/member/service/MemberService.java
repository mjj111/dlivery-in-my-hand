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

/**
 * User 도메인 로직을 처리 하는 서비스
 */
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberEntity register(MemberEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it ->{
                    userEntity.setStatus(MemberStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return memberRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));
    }

    public MemberEntity login(
            String email,
            String password
    ){
        var entity = getMemberWithThrow(email, password);
        return entity;
    }

    public MemberEntity getMemberWithThrow(
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