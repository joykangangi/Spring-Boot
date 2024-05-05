package com.jkangangi.db.repositories;

import com.jkangangi.db.entities.Customer;
import com.jkangangi.db.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //custom query
    List<Customer> findCustomerByDepartment(Department department);

}

