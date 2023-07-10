package org.delivery.db.member.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberStatus {

    REGISTERED("등록"),
    UNREGISTERED("탈퇴"),
    ;

    private final String description;
}