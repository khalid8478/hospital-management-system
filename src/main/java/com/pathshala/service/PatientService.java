package com.pathshala.service;

import com.pathshala.payload.request.PatientRequestDto;
import com.pathshala.payload.response.PatientResponseDto;

import java.util.List;

public interface PatientService {
    PatientResponseDto savePatient(PatientRequestDto dto);
    PatientResponseDto updatePatient(Long id, PatientRequestDto dto);
    List<PatientResponseDto> getAllPatients();
    PatientResponseDto getPatientById(Long id);
    void deletePatient(Long id);
}
