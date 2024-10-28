package com.assignment.tci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO for Employee Response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String empName;
    private Double amount;
    private String currency;
    private String joiningDate;
    private String exitDate;
    private String department;


    public EmployeeResponse(String raj_singh, double v, String inr, String s, String s1, String accounts) {
    }
}
