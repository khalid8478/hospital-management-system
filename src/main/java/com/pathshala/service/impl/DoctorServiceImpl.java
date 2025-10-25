package com.pathshala.service.impl;

import com.pathshala.entity.Department;
import com.pathshala.entity.Doctor;
import com.pathshala.mapper.DoctorMapper;
import com.pathshala.payload.request.DoctorRequestDto;
import com.pathshala.payload.response.DoctorResponseDto;
import com.pathshala.repository.DepartmentRepository;
import com.pathshala.repository.DoctorRepository;
import com.pathshala.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DoctorResponseDto createDoctor(DoctorRequestDto dto) {
        List<Department> departmentList = departmentRepository.findAllById(dto.getDepartmentIds());

        Set<Department> departments = new HashSet<Department>();
        if (departmentList != null) {
            for (Department department : departmentList) {
                departments.add(department);
            }
        }

        Doctor doctor = DoctorMapper.toEntity(dto, departments);
        Doctor saved = doctorRepository.save(doctor);
        return DoctorMapper.toResponseDto(saved);
    }

    @Override
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto dto) {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctor = null;
        if (optionalDoctor.isPresent()) {
            doctor = optionalDoctor.get();
        } else {
            throw new RuntimeException("Doctor not found");
        }


        List<Department> departmentList = departmentRepository.findAllById(dto.getDepartmentIds());
        Set<Department> departments = new HashSet<Department>();
        if (departmentList != null) {
            for (Department department : departmentList) {
                departments.add(department);
            }
        }

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setEmail(dto.getEmail());
        doctor.setDepartments(departments);

        Doctor updated = doctorRepository.save(doctor);
        return DoctorMapper.toResponseDto(updated);
    }

    @Override
    public List<DoctorResponseDto> getAllDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();

        List<DoctorResponseDto> dtoList = new ArrayList<DoctorResponseDto>();

        if (doctorList != null) {
            for (Doctor doctor : doctorList) {
                DoctorResponseDto dto = DoctorMapper.toResponseDto(doctor);
                dtoList.add(dto);
            }
        }

        return dtoList;
    }

    @Override
    public DoctorResponseDto getDoctorById(Long id) {
        Optional<Doctor> optionalDoctor = Optional.ofNullable(doctorRepository.findByIdWithDepartments(id));
        Doctor doctor = null;
        if (optionalDoctor.isPresent()) {
            doctor = optionalDoctor.get();
        } else {
            throw new RuntimeException("Doctor not found");
        }

        return DoctorMapper.toResponseDto(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
