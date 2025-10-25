package com.pathshala.controller;

import com.pathshala.payload.request.DoctorRequestDto;
import com.pathshala.payload.response.DoctorResponseDto;
import com.pathshala.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/save")
    public ResponseEntity<DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto dto) {
        return ResponseEntity.ok(doctorService.createDoctor(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorRequestDto dto) {
        DoctorResponseDto response = doctorService.updateDoctor(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        List<DoctorResponseDto> list = doctorService.getAllDoctors();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id) {
        DoctorResponseDto response = doctorService.getDoctorById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully with ID: " + id);
    }
}
