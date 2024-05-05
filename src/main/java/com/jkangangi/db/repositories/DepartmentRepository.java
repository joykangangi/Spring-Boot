package com.jkangangi.db.repositories;

import com.jkangangi.db.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    //custom query
    Optional<Department> findDepartmentByName(String name);
}
