package com.pathshala.mapper;

import com.pathshala.entity.Department;
import com.pathshala.entity.Doctor;
import com.pathshala.payload.request.DoctorRequestDto;
import com.pathshala.payload.response.DoctorResponseDto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DoctorMapper {

    public static Doctor toEntity(DoctorRequestDto dto, Set<Department> departments) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setEmail(dto.getEmail());
        doctor.setDepartments(departments);
        return doctor;
    }

    public static DoctorResponseDto toResponseDto(Doctor doctor) {
        DoctorResponseDto dto = new DoctorResponseDto();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setEmail(doctor.getEmail());


        Set<String> departmentNames = new HashSet<String>();
        if (doctor.getDepartments() != null) {
            Iterator<Department> iterator = doctor.getDepartments().iterator();
            while (iterator.hasNext()) {
                Department department = iterator.next();
                departmentNames.add(department.getName());
            }
        }

        dto.setDepartmentNames(departmentNames);
        return dto;
    }


}
