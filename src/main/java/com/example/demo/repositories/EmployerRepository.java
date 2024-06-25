package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

}