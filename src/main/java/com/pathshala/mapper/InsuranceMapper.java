package com.pathshala.mapper;

import com.pathshala.entity.Insurance;
import com.pathshala.payload.request.InsuranceRequestDto;
import com.pathshala.payload.response.InsuranceResponseDto;

public class InsuranceMapper {

    //Dto -> Entity
    public static Insurance toEntity(InsuranceRequestDto dto) {
        Insurance insurance = new Insurance();
        insurance.setInsuranceProvider(dto.getInsuranceProvider());
        insurance.setInsuranceNumber(dto.getInsuranceNumber());
        insurance.setValidUntil(dto.getValidUntil());

        return insurance;
    }

    //Entity -> DTO
    public static InsuranceResponseDto toResponseDto(Insurance insurance) {
        InsuranceResponseDto dto = new InsuranceResponseDto();
        dto.setId(insurance.getId());
        dto.setInsuranceProvider(insurance.getInsuranceProvider());
        dto.setInsuranceNumber(insurance.getInsuranceNumber());
        dto.setValidUntil(insurance.getValidUntil());

        return dto;
    }
}
