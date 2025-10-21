package com.pathshala.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {

    private Long id;
    private String name;
    private String gender;
    private String mobileNumber;
    private String email;
    private String insuranceProvider;
}
