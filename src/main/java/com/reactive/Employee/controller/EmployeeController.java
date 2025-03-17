package com.reactive.Employee.controller;


import com.reactive.Employee.dto.EmployeeDTO;
import com.reactive.Employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Mono<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping
    public Flux<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Mono<EmployeeDTO> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Mono<EmployeeDTO> updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteEmployee(@PathVariable String id) {
        return employeeService.deleteEmployee(id);
    }
}
