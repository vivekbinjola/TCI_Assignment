package com.assignment.tci.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empName;
    private Double amount;
    private String currency;

    private Date joiningDate;

    private Date exitDate;

    // Many-to-one relationship with department
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


}
