package com.jkangangi.controllers;

import com.jkangangi.db.entities.Customer;
import com.jkangangi.db.entities.Department;
import com.jkangangi.db.repositories.CustomerRepository;
import com.jkangangi.db.repositories.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


@RestController//make annotation restful so that they can be exposed as the API
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final DepartmentRepository departmentRepository;


    public CustomerController(
            CustomerRepository customerRepository, DepartmentRepository departmentRepository
    ) {
        this.customerRepository = customerRepository;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(
            @NonNull @PathVariable("customerId") Integer id,
            @NonNull @RequestBody Customer customer
    ) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            customerRepository.save(customer);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer not found or request body is null"
            );
        }
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping(path = "{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable("customerId") Integer id) {
        return customerRepository.findById(id);
    }


    @GetMapping(path = "department/{departmentName}")
    public Department getDepartment(
            @PathVariable("departmentName") String department
    ) {
         Optional<Department> foundDepartment = departmentRepository.findDepartmentByName(department);
         if (foundDepartment.isPresent()) {
             return foundDepartment.get();
         }
         else {
             throw new ResponseStatusException(
                     HttpStatus.NOT_FOUND,
                     "Department Not Found"
             );
         }
    }

}
