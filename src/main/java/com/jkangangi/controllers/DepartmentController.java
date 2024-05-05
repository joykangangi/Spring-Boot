package com.jkangangi.controllers;

import com.jkangangi.db.entities.Department;
import com.jkangangi.db.repositories.DepartmentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping(path = "{departmentName}")
    public Department getDepartmentByName(
            @PathVariable("departmentName") String name
    ) {
        return departmentRepository.findDepartmentByName(name).orElse(null);
    }
}
