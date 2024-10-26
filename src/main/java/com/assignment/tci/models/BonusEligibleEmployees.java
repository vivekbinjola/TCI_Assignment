package com.assignment.tci.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//To get data given in the given format, created BonusEligibleEmployees class and inner classes
// of the types requested in the pdf format
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonusEligibleEmployees {

    private String errorMessage;
    private List<EmployeeCurrencyGroup> data;


}
