package com.assignment.tci.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCurrencyGroup {
        private String currency;
        private List<EmployeeDetails> employees;

    }