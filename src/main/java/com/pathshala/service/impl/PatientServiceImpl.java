package com.pathshala.service.impl;

import com.pathshala.entity.Insurance;
import com.pathshala.entity.Patient;
import com.pathshala.mapper.PatientMapper;
import com.pathshala.payload.request.PatientRequestDto;
import com.pathshala.payload.response.PatientResponseDto;
import com.pathshala.repository.InsuranceRepository;
import com.pathshala.repository.PatientRepository;
import com.pathshala.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, InsuranceRepository insuranceRepository) {
        this.patientRepository = patientRepository;
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public PatientResponseDto savePatient(PatientRequestDto dto) {
        Insurance insurance = null;
        if(dto.getInsuranceId() != null) {
            insurance = insuranceRepository.findById(dto.getInsuranceId()).orElse(null);
        }
        Patient patient= PatientMapper.toEntity(dto, insurance);
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.toReponseDto(savedPatient);
    }

    @Override
    public PatientResponseDto updatePatient(Long id, PatientRequestDto dto) {
        Patient existingPatient = patientRepository.findById(id).get();
        if(existingPatient == null) {
            throw new RuntimeException("Patient not found with ID: " + id);
        }
        existingPatient.setName(dto.getName());
        existingPatient.setGender(dto.getGender());
        existingPatient.setMobileNumber(dto.getMobileNumber());
        existingPatient.setEmail(dto.getEmail());
        if(dto.getInsuranceId() != null) {
            Insurance insurance = insuranceRepository.findById(dto.getInsuranceId()).get();
            if (insurance != null){
                existingPatient.setInsurance(insurance);
            }
        }

        Patient updatedPatient = patientRepository.save(existingPatient);
        PatientResponseDto responseDto = PatientMapper.toReponseDto(updatedPatient);
        return responseDto;
    }

    @Override
    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDto> responseList = new ArrayList<>();

        for (Patient patient : patients) {
            PatientResponseDto dto = PatientMapper.toReponseDto(patient);
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public PatientResponseDto getPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(!optionalPatient.isPresent()) {
            throw new RuntimeException("Patient not found with ID" + id);
        }
        Patient patient = optionalPatient.get();
        PatientResponseDto dto = PatientMapper.toReponseDto(patient);
        return dto;
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
