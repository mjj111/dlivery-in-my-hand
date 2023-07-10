package org.delivery.db.member;

import org.delivery.db.member.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId,
                                                               MemberStatus status);

    Optional<MemberEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email,
                                                                             String password,
                                                                             MemberStatus status);

}