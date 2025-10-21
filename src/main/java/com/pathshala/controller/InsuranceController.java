package com.pathshala.controller;

import com.pathshala.payload.request.InsuranceRequestDto;
import com.pathshala.payload.response.InsuranceResponseDto;
import com.pathshala.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {

    private final InsuranceService insuranceService;
    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/save")
    public ResponseEntity<InsuranceResponseDto> saveInsurance(@RequestBody InsuranceRequestDto dto) {
        InsuranceResponseDto savedInsurance = insuranceService.saveInsurance(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInsurance);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InsuranceResponseDto>> getAllInsurances() {
        List<InsuranceResponseDto> insurances = insuranceService.getAllInsurances();
        return ResponseEntity.ok(insurances);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InsuranceResponseDto> getInsuranceById(@PathVariable Long id) {
        InsuranceResponseDto insurance = insuranceService.getInsuranceById(id);
        return ResponseEntity.ok(insurance);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InsuranceResponseDto> updateInsurance(@PathVariable Long id, @RequestBody InsuranceRequestDto dto) {
        InsuranceResponseDto updatedInsurance = insuranceService.updateInsurance(id, dto);
        return ResponseEntity.ok(updatedInsurance);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
        return ResponseEntity.status(HttpStatus.OK).body("Insurance deleted Successfully with ID: " + id);
    }
}
