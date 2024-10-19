package com.assignment.tci.dto;

import lombok.Data;

import java.util.List;

//DTO for Employee Request in the Post Method
@Data
public class EmployeeRequest {

    private List<EmployeeResponse> employees;

}
