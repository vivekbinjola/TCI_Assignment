package com.assignment.tci.dto;

import com.assignment.tci.models.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeRequest {

    private List<EmployeeDTO> employees;

}
