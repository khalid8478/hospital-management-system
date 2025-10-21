package com.pathshala.controller;

import com.pathshala.payload.request.AppointmentRequestDto;
import com.pathshala.payload.response.AppointmentResponseDto;
import com.pathshala.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Create Appointment
    @PostMapping("/save")
    public ResponseEntity<AppointmentRequestDto> createAppointment(@RequestBody AppointmentRequestDto dto) {
        AppointmentRequestDto created = appointmentService.createAppointment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update Appointment
    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentResponseDto dto) {

        AppointmentResponseDto updated = appointmentService.updateAppointment(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Get All Appointments
    @GetMapping("/all")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<AppointmentResponseDto> list = appointmentService.getAllAppointments();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Get Appointment by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDto dto = appointmentService.getAppointmentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Delete Appointment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>("Appointment deleted successfully with ID: " + id, HttpStatus.OK);
    }


}