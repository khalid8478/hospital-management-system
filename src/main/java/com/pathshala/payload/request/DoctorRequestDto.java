package com.pathshala.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto {

    private String name;
    private String specialization;
    private String email;
    private Set<Long> departmentIds;

}
