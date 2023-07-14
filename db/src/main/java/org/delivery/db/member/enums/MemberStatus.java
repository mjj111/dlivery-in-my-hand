package org.delivery.db.member.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberStatus {

    REGISTERED("틍록회원"),
    UNREGISTERED("탈퇴회원"),
    ;

    private final String description;
}