package com.pathshala.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceRequestDto {
    private String insuranceProvider;
    private LocalDate validUntil;
    private String insuranceNumber;
}
