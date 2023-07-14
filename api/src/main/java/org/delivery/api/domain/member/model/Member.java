package org.delivery.api.domain.member.model;

import lombok.*;
import org.delivery.db.member.enums.MemberStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member {

    private Long id;

    private String name;

    private String email;

    private String password;

    private MemberStatus status;

    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;
}