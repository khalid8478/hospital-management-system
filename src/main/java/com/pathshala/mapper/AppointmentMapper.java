package com.pathshala.mapper;

import com.pathshala.entity.Appointment;
import com.pathshala.entity.Patient;
import com.pathshala.payload.request.AppointmentRequestDto;
import com.pathshala.payload.response.AppointmentResponseDto;

public class AppointmentMapper {

    //DTO -> Entity
    public static Appointment toEntity(AppointmentRequestDto dto, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        appointment.setAppointmentReason(dto.getAppointmentReason());
        appointment.setPatient(patient);
        return appointment;

    }

    //Entity -> DTO
    public static AppointmentResponseDto toResponseDto(Appointment appointment) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setId(appointment.getId());
        dto.setAppointmentDateTime(appointment.getAppointmentDateTime());
        dto.setAppointmentReason(appointment.getAppointmentReason());
        dto.setPatientName(
                appointment.getPatient() != null ? appointment.getPatient().getName() : null
        );
        return dto;
    }
}
