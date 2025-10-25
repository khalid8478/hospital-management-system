package com.pathshala.service;

import com.pathshala.payload.request.DepartmentRequestDto;
import com.pathshala.payload.response.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDto createDepartment(DepartmentRequestDto dto);

    DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto);

    DepartmentResponseDto getDepartmentById(Long id);

    List<DepartmentResponseDto> getAllDepartments();

    void deleteDepartment(Long id);
}
