package com.grupoagoc.test.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grupoagoc.test.persist.Phone;
import com.grupoagoc.test.persist.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private Long id;
    @NotBlank
    @Max(250)
    private String name;
//    @NotBlank
    @Email(
        regexp = ".+[@].+[\\.].+",
        message = "please provide a valid email address")
    private String email;
//    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(
        regexp = "^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$",
        message = "The password needs at between 8-20 characters, and must include at least:" +
            "  - 1 number" +
            "  - 1 Capital letter" +
            "  - 1 Minus letter" +
            "  - 1 special Character from the next ones: @, #, $, %")
    private String password;
    @Valid
    private Set<PhoneRequest> phones;

    @JsonIgnore
    public final User newRegister(){
        LocalDateTime now = LocalDateTime.now();
        return User.builder()
            .userId(id)
            .name(name)
            .email(email)
            .password(password)
            .phones(toPhones())
            .created(now)
            .modified(now)
            .lastLogin(now)
            .isActive(true)
            .build();
    }

    @JsonIgnore
    public final User updateRegister(){
        LocalDateTime now = LocalDateTime.now();
        return User.builder()
            .userId(id)
            .name(name)
            .email(email)
            .password(password)
            .phones(toPhones())
            .modified(now)
            .lastLogin(now)
            .build();
    }

    @JsonIgnore
    private final Set<Phone> toPhones(){
        return phones.stream().map(p -> {
            return p.toPhone();
        }).collect(Collectors.toSet());
    }
}
