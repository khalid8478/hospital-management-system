package com.pathshala.service.impl;

import com.pathshala.entity.Appointment;
import com.pathshala.entity.Patient;
import com.pathshala.mapper.AppointmentMapper;
import com.pathshala.payload.request.AppointmentRequestDto;
import com.pathshala.payload.response.AppointmentResponseDto;
import com.pathshala.repository.AppointmentRepository;
import com.pathshala.repository.PatientRepository;
import com.pathshala.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = AppointmentMapper.toEntity(dto, patient);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        return AppointmentMapper.toResponseDto(savedAppointment);
    }



    @Override
    public AppointmentResponseDto updateAppointment(Long id, AppointmentResponseDto dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        appointment.setAppointmentReason(dto.getAppointmentReason());
        appointmentRepository.save(appointment);

        return AppointmentMapper.toResponseDto(appointment);
    }

    @Override
    public List<AppointmentResponseDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentResponseDto> dtoList = new ArrayList<>();

        for (Appointment appointment : appointments) {
            AppointmentResponseDto dto = AppointmentMapper.toResponseDto(appointment);
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public AppointmentResponseDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        return AppointmentMapper.toResponseDto(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
