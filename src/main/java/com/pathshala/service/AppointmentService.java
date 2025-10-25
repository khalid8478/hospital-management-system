package com.pathshala.service;

import com.pathshala.payload.request.AppointmentRequestDto;
import com.pathshala.payload.response.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDto  createAppointment(AppointmentRequestDto dto);
    AppointmentResponseDto updateAppointment(Long id, AppointmentResponseDto dto);
    List<AppointmentResponseDto> getAllAppointments();
    AppointmentResponseDto getAppointmentById(Long id); // নতুন মেথড
    void deleteAppointment(Long id);
}
