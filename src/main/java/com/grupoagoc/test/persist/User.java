package com.grupoagoc.test.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "UserTest")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @JsonIgnore
    @NotBlank
    @Max(250)
    private String name;
    @JsonIgnore
    @NotBlank
    @Email(
        regexp = ".+[@].+[\\.].+",
        message = "please provide a valid email address")
    private String email;
    @JsonIgnore
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(
        regexp = "^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$",
        message = "The password needs at between 8-20 characters, and must include at least:" +
                  "  - 1 number" +
                  "  - 1 Capital letter" +
                  "  - 1 Minus letter" +
                  "  - 1 special Character from the next ones: @, #, $, %")
    private String password;
    @JsonIgnore
    @Valid
    @OneToMany(mappedBy="user",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private Set<Phone> phones;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private UUID token;
    private Boolean isActive;
}
