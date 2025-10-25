package com.pathshala.service;

import com.pathshala.payload.request.DoctorRequestDto;
import com.pathshala.payload.response.DoctorResponseDto;
import java.util.List;

public interface DoctorService {

    DoctorResponseDto createDoctor(DoctorRequestDto dto);

    DoctorResponseDto updateDoctor(Long id, DoctorRequestDto dto);

    List<DoctorResponseDto> getAllDoctors();

    DoctorResponseDto getDoctorById(Long id);

    void deleteDoctor(Long id);

}
