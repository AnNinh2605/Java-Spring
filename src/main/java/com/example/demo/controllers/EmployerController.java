package com.example.demo.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Employer;
import com.example.demo.services.EmployerService;
import com.example.demo.response.CustomResponse;

@RestController
@RequestMapping(path = "/api/v1/employers")
@Validated
public class EmployerController {
    @Autowired
    private EmployerService employerService;

    // create employer
    @PostMapping("")
    public ResponseEntity<CustomResponse> createEmployer(@Valid @RequestBody Employer employer) {
            return employerService.createEmployer(employer);
    }

    // get all employers
    @GetMapping("")
    public ResponseEntity<CustomResponse> getAllEmployer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employerService.getAllEmployers(pageable);
    }

    // get employer by Id
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getUserById(@PathVariable Long id) {
        return employerService.getEmployerById(id);
    }

    // update employer
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateEmployer(@PathVariable Long id, @RequestBody Employer employer) {
        return employerService.updateEmployer(id, employer);
    }

    // delete employer
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteEmployer(@PathVariable Long id) {
        return employerService.deleteEmployer(id);
    }
}
