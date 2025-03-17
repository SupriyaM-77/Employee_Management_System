package com.reactive.Employee.repository;


import com.reactive.Employee.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
    Flux<Employee> findByDepartment(String department);
}
