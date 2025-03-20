package com.reactive.Employee.service;


import com.reactive.Employee.dto.EmployeeDTO;
import com.reactive.Employee.model.Employee;
import com.reactive.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Flux<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().map(this::convertToDTO);
    }

    public Mono<EmployeeDTO> getEmployeeById(String id) {
        return employeeRepository.findById(id).map(this::convertToDTO);
    }

    public Mono<EmployeeDTO> createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        return employeeRepository.save(employee).map(this::convertToDTO);
    }

    public Mono<EmployeeDTO> updateEmployee(String id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id)
                .flatMap(existingEmployee -> {
                    existingEmployee.setName(employeeDTO.getName());
                    existingEmployee.setDepartment(employeeDTO.getDepartment());
                    existingEmployee.setSalary(employeeDTO.getSalary());
                    return employeeRepository.save(existingEmployee);
                })
                .map(this::convertToDTO);
    }


    public Mono<Void> deleteEmployee(String id) {
        return employeeRepository.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary());
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getDepartment(), employeeDTO.getSalary());
    }



}
