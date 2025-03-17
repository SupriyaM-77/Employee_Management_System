package com.reactive.Employee.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private String id;
    private String name;
    private String department;
    private double salary;
}

