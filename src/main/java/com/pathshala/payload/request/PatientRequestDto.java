package com.pathshala.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto {

    private String name;
    private String gender;
    private String mobileNumber;
    private String email;
    private Long insuranceId;
}
