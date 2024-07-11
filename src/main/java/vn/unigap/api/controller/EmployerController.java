package vn.unigap.api.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import vn.unigap.api.dto.in.CreateEmployerDtoIn;
import vn.unigap.api.dto.in.IdEmployerDtoIn;
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
    @PostMapping("")
    public ResponseEntity<?> createEmployer(@Valid @RequestBody CreateEmployerDtoIn employer) {
        try {
            String responseService = employerService.createEmployer(employer);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomResponse<>(0, HttpStatus.CREATED.value(), responseService, ""));
        } catch (DuplicateKeyException e) {
            log.error("An error occurred at controller", e);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new CustomResponse<>(2, HttpStatus.CONFLICT.value(), e.getMessage(), ""));
        } catch (Exception e) {
            log.error("An error occurred at service", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(5, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), ""));
        }
    }

    // get all employers
    @GetMapping("")
    public ResponseEntity<?> getAllEmployer(@Valid @ModelAttribute PaginateEmployerDtoIn paginateEmployerDtoIn) {
        try {
            PaginationResponse responseService = employerService.getAllEmployers(paginateEmployerDtoIn);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse<>(0, HttpStatus.OK.value(), "Get " +
                            "employer list successfully", responseService));
        } catch (Exception e) {
            log.info("An error occurred at controller", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(5, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), ""));
        }
    }

    // get employer by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            Object responseService = employerService.getEmployerById(id);

            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse<>(0, HttpStatus.OK.value(), "Get " +
                    "employer by Id successful", responseService));
        } catch (EntityNotFoundException e) {
            log.error("An error occurred at controller", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse<>(1, HttpStatus.NOT_FOUND.value(), e.getMessage(), ""));
        } catch (Exception e) {
            log.error("An error occurred at controller", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(5, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), ""));
        }
    }

    // update employer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployer(@PathVariable Long id,
                                            @Valid @RequestBody UpdateEmployerDtoIn employer) {
        try {
            String responseService = employerService.updateEmployer(id, employer);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse<>(0, HttpStatus.OK.value(), responseService, ""));
        } catch (EntityNotFoundException e) {
            log.error("check not found " + e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse<>(1, HttpStatus.NOT_FOUND.value(), e.getMessage(), ""));
        } catch (Exception e) {
            log.error("An error occurred at controller", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(5, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), ""));
        }
    }

    // delete employer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployer(@PathVariable Long id) {
        try {
            String responseService = employerService.deleteEmployer(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse<>(0, HttpStatus.OK.value(), responseService, ""));
        } catch (EntityNotFoundException e) {
            log.error("check not found " + e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse<>(1, HttpStatus.NOT_FOUND.value(), e.getMessage(), ""));
        } catch (Exception e) {
            log.error("An error occurred at controller", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomResponse<>(5, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), ""));
        }
    }
}
