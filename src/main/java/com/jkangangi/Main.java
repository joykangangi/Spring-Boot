package com.jkangangi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SpringBootApplication
@RestController//make annotation restful so that they can be exposed as the API
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            Integer age,
            String email,
            List<String> programmingLanguages,
            Department department
    ) {

    }


    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customer.setProgrammingLanguages(request.programmingLanguages);
        customer.setDepartment(request.department);
        customerRepository.save(customer);
    }


    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    record UpdateCustomerRequest(
            String name,
            Integer age,
            String email,
            List<String> programmingLanguages,
            Department department
    ) {

    }

    @PutMapping("{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Integer id,
            @RequestBody UpdateCustomerRequest updateRequest
    ) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent() && updateRequest != null) {
            Customer customer = optionalCustomer.get();
            customer.setName(updateRequest.name);
            customer.setAge(updateRequest.age);
            customer.setEmail(updateRequest.email);
            customer.setProgrammingLanguages(updateRequest.programmingLanguages);
            customer.setDepartment(updateRequest.department);
            customerRepository.save(customer);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer not found or request body is null"
            );
        }
    }


}


//    @GetMapping("/greet")
//    public GreetResponse greet() {
//        GreetResponse response = new GreetResponse(
//                "Hello Jo ",
//                List.of("Kotlin", "Python", "Java", "MATLAB"),
//                new Person(
//                        "Jo Coder",
//                        12,
//                        "Logickoder's PA"
//                )
//        );
//
//        return response;
//    }
//
//    record Person(
//            String name,
//            Integer age,
//            String description
//    ) {
//
//    }
//
//    record GreetResponse(
//            String greet,
//            List<String> favProgrammingLanguages,
//            Person person
//    ) {
//
//    }
//    //record GreetResponse(String greet) {}
////    class GreetResponse {
////        private final String greet;
////
////        GreetResponse(String greet) {
////            this.greet = greet;
////        }
////
////        public String getGreet() {
////            return greet;
////        }
////
////        @Override
////        public String toString() {
////            return "GreetResponse{"+
////                    "greet='"+ greet + '\'' +
////                    '}';
////        }
////
////        @Override
////        public boolean equals(Object o) {
////            if (this == o) return true;
////            if (o == null || getClass() != o.getClass()) return false;
////            GreetResponse that = (GreetResponse) o;
////            return Objects.equals(greet, that.greet);
////        }
////
////        @Override
////        public int hashCode() {
////            return Objects.hashCode(greet);
////        }
////
////
////    }
//}
