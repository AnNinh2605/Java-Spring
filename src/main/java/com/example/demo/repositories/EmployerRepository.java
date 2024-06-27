package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Optional<EmployerProjection> findEmployerById(Long id);

    Optional<Employer> findEmployerByEmail(String employerEmail);

    Page<EmployerProjection> findAllEmployerByOrderByName(Pageable pageable);
}