package vn.unigap.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vn.unigap.api.dto.in.CreateEmployerDtoIn;
import vn.unigap.api.dto.in.PaginateEmployerDtoIn;
import vn.unigap.api.dto.in.UpdateEmployerDtoIn;
import vn.unigap.api.dto.out.PaginationResponse;
import vn.unigap.api.entity.Employer;
import vn.unigap.api.repository.EmployerProjection;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.unigap.api.repository.EmployerRepository;
import vn.unigap.api.repository.EmployerRepositoryPage;
import vn.unigap.api.repository.GetEmployerByIdProjection;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class EmployerService {
    // Constructor Injection
    EmployerRepository employerRepository;
    EmployerRepositoryPage employerRepositoryPage;

    private Employer convertToEntity(CreateEmployerDtoIn dtoIn) {
        Employer employer = new Employer();
        employer.setName(dtoIn.getName());
        employer.setEmail(dtoIn.getEmail());
        employer.setProvince(dtoIn.getProvinceId());
        employer.setDescription(dtoIn.getDescription());
        return employer;
    }

    // create employer
    public String createEmployer(CreateEmployerDtoIn employer) {
        String email = employer.getEmail();

        try {
            // check exist email
            if (employerRepository.existsByEmail(email)) {
                throw new DuplicateKeyException("Email already exists: " + email);
            }

            // check exist provinceId

            // create employer
            Employer createData = convertToEntity(employer);
            employerRepository.save(createData);

            return "Create employer successful";
        } catch (DuplicateKeyException e) {
            log.error("An error occurred at service", e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred at service", e);
            throw new RuntimeException("Failed to create employer", e);
        }
    }

    // update employer
    public String updateEmployer(Long id, UpdateEmployerDtoIn employerDetails) {
        String name = employerDetails.getName();
        int province = employerDetails.getProvinceId();
        String description = employerDetails.getDescription();

        try {
            // find employer
            Optional<Employer> findEmployer = employerRepository.findById(id);
            if (findEmployer.isEmpty()) {
                throw new EntityNotFoundException("Employer not found " + id);
            }

            // check existing provinceId

            // update employer
            Employer updateData = findEmployer.get();
            updateData.setName(name);
            updateData.setProvince(province);
            updateData.setDescription(description);

            employerRepository.save(updateData);
            return "Update employer successful";
        } catch (EntityNotFoundException e) {
            log.error("An error occurred at service", e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred at service", e);
            throw new RuntimeException("Failed to update employer", e);
        }
    }

    // get employer by id
    public Optional<GetEmployerByIdProjection> getEmployerById(Long id) {
        try {
            Optional<GetEmployerByIdProjection> employer = employerRepository.findEmployerById(id);
            if (employer.isEmpty()) {
                throw new EntityNotFoundException("Employer not found id = " + id);
            }
            // missing provinceName in data response
            return employer;
        } catch (EntityNotFoundException e) {
            log.error("An error occurred at service", e);
            throw e;
        } catch (Exception e) {
            log.info("An error occurred at service", e);
            throw new RuntimeException("Failed to get employer by id", e);
        }
    }

    // get employer list
    public PaginationResponse getAllEmployers(PaginateEmployerDtoIn paginateEmployerDtoIn) {
        Pageable pageable = PageRequest.of(paginateEmployerDtoIn.getPage() - 1, paginateEmployerDtoIn.getPageSize());
        try {

            Page<EmployerProjection> employerList = employerRepositoryPage.findAllEmployerByOrderByName(pageable);

            List<EmployerProjection> data = employerList.getContent();
            int page = employerList.getNumber();
            int pageSize = employerList.getSize();
            long totalElements = employerList.getTotalElements();
            long totalPages = employerList.getTotalPages();

            // missing provinceName in data response
            return new PaginationResponse(page, pageSize, totalElements, totalPages, data);
        } catch (Exception e) {
            log.info("An error occurred at service", e);
            throw new RuntimeException("Failed to get employer list", e);
        }
    }

    // delete employer
    public String deleteEmployer(Long id) {
        try {
            boolean exists = employerRepository.existsById(id);
            if (!exists) {
                throw new EntityNotFoundException("Not found employer " + id);
            }

            employerRepository.deleteById(id);
            return "Delete employer successful";
        } catch (EntityNotFoundException e) {
            log.error("An error occurred at service", e);
            throw e;
        } catch (Exception e) {
            log.info("An error occurred at service", e);
            throw new RuntimeException("Failed to delete employer", e);
        }
    }
}
