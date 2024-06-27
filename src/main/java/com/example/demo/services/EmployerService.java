package com.example.demo.services;

import com.example.demo.repositories.EmployerProjection;
import com.example.demo.response.CustomResponse;
import com.example.demo.models.Employer;
import com.example.demo.repositories.EmployerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    // create employer
    public ResponseEntity<CustomResponse> createEmployer(Employer employer) {
        // check exist email
        Optional<Employer> findEmployer = employerRepository.findEmployerByEmail(employer.getEmail().trim());
        if (findEmployer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new CustomResponse(2, HttpStatus.CONFLICT.value(), "Email already exist", ""));
        }
        // check exist provinceId

        // create employer
        employerRepository.save(employer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomResponse(0, HttpStatus.CREATED.value(), "Create employer successful", ""));
    }

    // update employer
    public ResponseEntity<CustomResponse> updateEmployer(Long id, Employer employerDetails) {
        // find employer
        Optional<Employer> findEmployer = employerRepository.findById(id);
        if (findEmployer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(1, HttpStatus.NOT_FOUND.value(), "Employer not found", ""));
        }

        // update employer
        Employer existingEmployer = findEmployer.get();
        existingEmployer.setName(employerDetails.getName());
        existingEmployer.setProvinceId(employerDetails.getProvinceId());
        existingEmployer.setDescription(employerDetails.getDescription());

        employerRepository.save(existingEmployer);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse(0, HttpStatus.OK.value(), "Update employer successful", ""));
    }

    // get employer by id
    public ResponseEntity<CustomResponse> getEmployerById(Long id) {
        Optional<EmployerProjection> employer = employerRepository.findEmployerById(id);
        return employer.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(0, HttpStatus.OK.value(), "Get employer by Id successful", employer))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(1, HttpStatus.NOT_FOUND.value(), "Employer not found: id = " + id, ""));
        // missing provinceName in data response
    }

    // get employer list
    public ResponseEntity<CustomResponse> getAllEmployers(Pageable pageable) {
        Page<EmployerProjection> employerList = employerRepository.findAllEmployerByOrderByName(pageable);

        List<EmployerProjection> data = employerList.getContent();
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(0, HttpStatus.OK.value(), "Get employerlist successful", data));

        // did not format response as required
        // missing provinceName in data response
    }

    // delete employer
    public ResponseEntity<CustomResponse> deleteEmployer(Long id) {
        boolean exists = employerRepository.existsById(id);
        if (exists) {
            employerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse(0, HttpStatus.OK.value(), "Delete employer successful", ""));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomResponse(1, HttpStatus.NOT_FOUND.value(), "Not found employer", ""));
    }
}
