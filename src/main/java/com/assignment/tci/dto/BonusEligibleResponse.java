package com.assignment.tci.dto;

import lombok.Data;

import java.util.List;

//To get data given in the given format, created BonusEligibleResponse class and inner classes
// of the types requested in the pdf format
@Data
public class BonusEligibleResponse {

    private String errorMessage;
    private List<CurrencyGroup> data;

    public BonusEligibleResponse(String errorMessage, List<CurrencyGroup> data) {
        this.errorMessage = errorMessage;
        this.data = data;
    }

    @Data
    public static class CurrencyGroup {
        private String currency;
        private List<EmployeeRequestBonus> employees;

        public CurrencyGroup(String currency, List<EmployeeRequestBonus> employees) {
            this.currency = currency;
            this.employees = employees;
        }

    }
    @Data
    public static class EmployeeRequestBonus {
        private String empName;
        private Double amount;

        public EmployeeRequestBonus(String empName, Double amount) {
            this.empName = empName;
            this.amount = amount;
        }

    }
}
