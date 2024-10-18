package com.assignment.tci.dto;

import com.assignment.tci.models.Department;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {

    private Long id;

    private String empName;
    private Double amount;
    private String currency;
    private String joiningDate;
    private String exitDate;
    private String department;

    public EmployeeDTO(String empName, Double amount, String currency, String joiningDate, String exitDate, String department) {
        this.empName = empName;
        this.amount = amount;
        this.currency = currency;
        this.joiningDate = joiningDate;
        this.exitDate = exitDate;
        this.department = department;
    }


}
