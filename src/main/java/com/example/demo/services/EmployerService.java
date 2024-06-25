package com.example.demo.services;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Employer;
import com.example.demo.repositories.EmployerRepository;

@Service
public class EmployerService {
    private EmployerRepository employerRepository;

    public Employer getEmployerById(Long id){
        Optional<Employer> employer = employerRepository.findById(id);
        if (employer.isPresent()) {
            return employer.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Employer createEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer updateEmployer(Long id, Employer employerDetails) {
        Employer employer = getEmployerById(id);
        employer.setName(employerDetails.getName());
        return employerRepository.save(employer);
    }

//    public Employer deleteEmployer(Long id) {
//        employerRepository.deleteById(id);
//    }
}
