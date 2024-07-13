package vn.unigap.api.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import vn.unigap.api.dto.in.CreateEmployerDtoIn;
import vn.unigap.api.dto.in.PaginateEmployerDtoIn;
import vn.unigap.api.dto.in.UpdateEmployerDtoIn;
import vn.unigap.api.dto.out.CustomResponse;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import vn.unigap.api.dto.out.PaginationResponse;
import vn.unigap.api.service.EmployerService;

@RestController
@RequestMapping(path = "/api/v1/employers")
@RequiredArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
@Slf4j
public class EmployerController {
    // Constructor Injection
    EmployerService employerService;

    // create employer
    @PostMapping
    public ResponseEntity<?> createEmployer(@Valid @RequestBody CreateEmployerDtoIn employer) throws DuplicateKeyException, EntityNotFoundException {
        String responseService = employerService.createEmployer(employer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomResponse<>(0, HttpStatus.CREATED.value(), responseService, ""));
    }

    // get all employers
    @GetMapping
    public ResponseEntity<?> getAllEmployer(@Valid @ModelAttribute PaginateEmployerDtoIn paginateEmployerDtoIn) {
        PaginationResponse responseService = employerService.getAllEmployers(paginateEmployerDtoIn);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(0, HttpStatus.OK.value(), "Get " +
                        "employer list successfully", responseService));
    }

    // get employer by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws EntityNotFoundException {
        Object responseService = employerService.getEmployerById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>(0, HttpStatus.OK.value(), "Get " +
                "employer by Id successful", responseService));
    }

    // update employer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployer(@PathVariable Long id,
                                            @Valid @RequestBody UpdateEmployerDtoIn employer) throws EntityNotFoundException {
        String responseService = employerService.updateEmployer(id, employer);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(0, HttpStatus.OK.value(), responseService, ""));
    }

    // delete employer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployer(@PathVariable Long id) throws EntityNotFoundException {
        String responseService = employerService.deleteEmployer(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponse<>(0, HttpStatus.OK.value(), responseService, ""));
    }
}
