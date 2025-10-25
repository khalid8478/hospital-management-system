package com.pathshala.mapper;

import com.pathshala.entity.Department;
import com.pathshala.entity.Doctor;
import com.pathshala.payload.request.DepartmentRequestDto;
import com.pathshala.payload.response.DepartmentResponseDto;

import java.util.HashSet;
import java.util.Set;

public class DepartmentMapper {
    // 1️⃣ Entity -> Response DTO
    public static DepartmentResponseDto toResponseDto(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setName(department.getName());

        Set<String> doctorNames = new HashSet<>();
        if(department.getDoctors() != null){
            for (Doctor doctor : department.getDoctors()) {
                doctorNames.add(doctor.getName());
            }
        }
        dto.setDoctorNames(doctorNames);

        return dto;
    }

    // 2️⃣ Request DTO + Set<Doctor> -> Entity
    public static Department toEntity(DepartmentRequestDto dto, Set<Doctor> doctors) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setDoctors(doctors != null ? doctors : new HashSet<>());
        return department;
    }
}
