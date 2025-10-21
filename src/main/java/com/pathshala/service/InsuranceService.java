package com.pathshala.service;


import com.pathshala.payload.request.InsuranceRequestDto;
import com.pathshala.payload.response.InsuranceResponseDto;

import java.util.List;

public interface InsuranceService {
    InsuranceResponseDto saveInsurance (InsuranceRequestDto dto);
    List<InsuranceResponseDto> getAllInsurances();
    InsuranceResponseDto getInsuranceById(Long id);
    InsuranceResponseDto updateInsurance(Long id, InsuranceRequestDto dto);
    void deleteInsurance(Long id);

}
