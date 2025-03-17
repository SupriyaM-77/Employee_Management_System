package com.reactive.Employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private String department;
    private double salary;
}
