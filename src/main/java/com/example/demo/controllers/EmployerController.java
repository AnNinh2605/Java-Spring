package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Employer;
import com.example.demo.services.EmployerService;

@RestController
@RequestMapping(path = "/")
public class EmployerController {
    private EmployerService employerService;

    @GetMapping("/users/{id}")
    public Employer getUserById(@PathVariable Long id) {
        return employerService.getEmployerById(id);
    }

    @PostMapping("/users")
    public Employer createUser(@RequestBody Employer user) {
        return employerService.createEmployer(user);
    }

    @PutMapping("/users/{id}")
    public Employer updateEmployer(@PathVariable Long id, @RequestBody Employer employer){
        return employerService.updateEmployer(id, employer);
    }

//    @DeleteMapping("/users/{id}")
//    public Employer deleteEmployer(@PathVariable Long id){
//        employerService.deleteEmployer(id);
//    }
}
