package com.pathshala.service.impl;

import com.pathshala.entity.Department;
import com.pathshala.entity.Doctor;
import com.pathshala.mapper.DepartmentMapper;
import com.pathshala.payload.request.DepartmentRequestDto;
import com.pathshala.payload.response.DepartmentResponseDto;
import com.pathshala.repository.DepartmentRepository;
import com.pathshala.repository.DoctorRepository;
import com.pathshala.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    private DoctorRepository doctorRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {
        Department department = new Department();
        department.setName(dto.getName());

        Set<Doctor> doctors = new HashSet<>();
        if(dto.getDoctorIds() != null) {
            for (Long id : dto.getDoctorIds()) {
                Doctor doctor = doctorRepository.findById(id).orElse(null);
                if(doctor != null) doctors.add(doctor);
            }
        }

        department.setDoctors(doctors);

        Department saved = departmentRepository.save(department);
        return DepartmentMapper.toResponseDto(saved);
    }

    @Override
    public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = null;
        if(optionalDepartment.isPresent()) {
            department = optionalDepartment.get();
        } else {
            throw new RuntimeException("Department not found");
        }

        department.setName(dto.getName());

        Set<Doctor> doctors = new HashSet<Doctor>();
        if(dto.getDoctorIds() != null) {
            for (Long docId : dto.getDoctorIds()) {
                Optional<Doctor> optionalDoctor = doctorRepository.findById(docId);
                Doctor doctor = null;
                if (optionalDoctor.isPresent()) {
                    doctor = optionalDoctor.get();
                    doctors.add(doctor);
                }
            }
        }

        department.setDoctors(doctors);

        Department updated = departmentRepository.save(department);

        return DepartmentMapper.toResponseDto(updated);
    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long id) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = null;

        if (optionalDepartment.isPresent()) {
            department = optionalDepartment.get();
        } else {
            throw new RuntimeException("Department not found");
        }

        DepartmentResponseDto responseDto = DepartmentMapper.toResponseDto(department);
        return responseDto;
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();
        List<DepartmentResponseDto> responses = new ArrayList<>();
        for (Department dept : departments) {
            responses.add(DepartmentMapper.toResponseDto(dept));
        }
        return responses;
    }

    @Override
    public void deleteDepartment(Long id) {
    departmentRepository.deleteById(id);
    }
}
