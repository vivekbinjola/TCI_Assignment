package com.assignment.tci.dto;

import lombok.Data;

import java.util.List;

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
        private List<EmployeeDTOBonus> employees;

        public CurrencyGroup(String currency, List<EmployeeDTOBonus> employees) {
            this.currency = currency;
            this.employees = employees;
        }

    }
    @Data
    public static class EmployeeDTOBonus {
        private String empName;
        private Double amount;

        public EmployeeDTOBonus(String empName, Double amount) {
            this.empName = empName;
            this.amount = amount;
        }

    }
}
