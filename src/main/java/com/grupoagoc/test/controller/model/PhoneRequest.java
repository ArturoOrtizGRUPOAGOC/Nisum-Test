package com.grupoagoc.test.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grupoagoc.test.persist.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneRequest {
    @NotNull
    @Size(min = 8, max = 12)
    private Integer number;
    @NotNull
    @Size(min = 1, max = 5)
    private Integer cityCode;
    @NotNull
    @Size(min = 3, max = 3)
    private Integer countryCode;

    @JsonIgnore
    public final Phone toPhone(){
        return Phone.builder()
            .number(number)
            .cityCode(cityCode)
            .countryCode(countryCode)
            .build();
    }
}
