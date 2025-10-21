package com.pathshala.controller;

import com.pathshala.payload.request.PatientRequestDto;
import com.pathshala.payload.response.PatientResponseDto;
import com.pathshala.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Create
    @PostMapping("/save")
    public ResponseEntity<PatientResponseDto> savedPatient(@RequestBody PatientRequestDto dto) {
        PatientResponseDto savedPatient = patientService.savePatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    //Get All
    @GetMapping("/all")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        List<PatientResponseDto> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
    //Get By id
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {
        PatientResponseDto patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    //Update
    @PutMapping("update/{id}")
    public PatientResponseDto updatePatient(@PathVariable Long id, @RequestBody PatientRequestDto dto) {
        return patientService.updatePatient(id, dto);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaitent(@PathVariable Long id) {
        patientService.deletePatient(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Patient deleted successfully with ID: " + id);
    }

}
