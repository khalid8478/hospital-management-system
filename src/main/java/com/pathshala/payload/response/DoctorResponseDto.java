package com.pathshala.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDto {

    private Long id;

    private String name;

    private String specialization;

    private String email;

    private Set<String> departmentNames;
}

