package com.pathshala.mapper;

import com.pathshala.entity.Insurance;
import com.pathshala.entity.Patient;
import com.pathshala.payload.request.PatientRequestDto;
import com.pathshala.payload.response.PatientResponseDto;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDto dto, Insurance insurance) {

        //DTO -> Entity
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setMobileNumber(dto.getMobileNumber());
        patient.setEmail(dto.getEmail());
        patient.setInsurance(insurance);
        return patient;
    }

    //Entity -> DTO
    public static PatientResponseDto toReponseDto(Patient patient) {
        PatientResponseDto dto = new PatientResponseDto();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setGender(patient.getGender());
        dto.setMobileNumber(patient.getMobileNumber());
        dto.setEmail(patient.getEmail());
        dto.setInsuranceProvider(
                patient.getInsurance() != null ? patient.getInsurance().getInsuranceProvider() : null
        );

        return dto;
    }


}
