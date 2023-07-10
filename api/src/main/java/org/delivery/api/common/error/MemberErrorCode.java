package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Member에 대한 에러는 1000번대를 사용할 것이다.
 */
@AllArgsConstructor
@Getter
public enum MemberErrorCode implements ErrorCodeIfs{

    MEMBER_NOT_FOUND(400 , 1404 , "회원을 찾을 수 없음."),

    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}