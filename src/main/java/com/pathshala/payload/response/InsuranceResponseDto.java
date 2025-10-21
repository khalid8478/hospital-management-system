package com.pathshala.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponseDto {
    private Long id;
    private String insuranceProvider;
    private LocalDate validUntil;
    private String insuranceNumber;
}

