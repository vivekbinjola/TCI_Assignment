package com.assignment.tci.dto;

import lombok.Data;

//DTO for Employee Response
@Data
public class EmployeeResponse {

    private Long id;

    private String empName;
    private Double amount;
    private String currency;
    private String joiningDate;
    private String exitDate;
    private String department;

    public EmployeeResponse(String empName, Double amount, String currency, String joiningDate, String exitDate, String department) {
        this.empName = empName;
        this.amount = amount;
        this.currency = currency;
        this.joiningDate = joiningDate;
        this.exitDate = exitDate;
        this.department = department;
    }

}
