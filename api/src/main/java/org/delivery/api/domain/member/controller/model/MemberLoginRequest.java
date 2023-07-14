package org.delivery.api.domain.member.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}