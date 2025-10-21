package com.pathshala.service.impl;

import com.pathshala.entity.Insurance;
import com.pathshala.mapper.InsuranceMapper;
import com.pathshala.payload.request.InsuranceRequestDto;
import com.pathshala.payload.response.InsuranceResponseDto;
import com.pathshala.repository.InsuranceRepository;
import com.pathshala.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }


    @Override
    public InsuranceResponseDto saveInsurance(InsuranceRequestDto dto) {
        Insurance insurance = InsuranceMapper.toEntity(dto);
        Insurance savedInsurance = insuranceRepository.save(insurance);
        return  InsuranceMapper.toResponseDto(savedInsurance);
    }

    @Override
    public List<InsuranceResponseDto> getAllInsurances() {
        List<Insurance> insurances = insuranceRepository.findAll();
        List<InsuranceResponseDto> responseList = new ArrayList<>();
        for(Insurance insurance : insurances) {
            InsuranceResponseDto dto = InsuranceMapper.toResponseDto(insurance);
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public InsuranceResponseDto getInsuranceById(Long id) {
        Optional<Insurance> optionalInsurance = insuranceRepository.findById(id);
        if(optionalInsurance.isPresent()) {
            Insurance insurance = optionalInsurance.get();
            return InsuranceMapper.toResponseDto(insurance);
        } else {
            throw new RuntimeException("Insurance not found");
        }
    }

    @Override
    public InsuranceResponseDto updateInsurance(Long id, InsuranceRequestDto dto) {
        Optional<Insurance> optionalInsurance = insuranceRepository.findById(id);

        if (!optionalInsurance.isPresent()) {
            throw new RuntimeException("Insurance not found with ID: " + id);
        }
        Insurance existingInsurance = optionalInsurance.get();

        existingInsurance.setInsuranceProvider(dto.getInsuranceProvider());
        existingInsurance.setValidUntil(dto.getValidUntil());
        existingInsurance.setInsuranceNumber(dto.getInsuranceNumber());

        Insurance updatedInsurance = insuranceRepository.save(existingInsurance);
        return InsuranceMapper.toResponseDto(updatedInsurance);
    }

    @Override
    public void deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
    }
}
