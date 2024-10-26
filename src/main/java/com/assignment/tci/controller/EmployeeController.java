package com.assignment.tci.controller;

import com.assignment.tci.models.BonusEligibleEmployees;
import com.assignment.tci.dto.EmployeeRequest;
import com.assignment.tci.models.EmployeeCurrencyGroup;
import com.assignment.tci.repository.EmployeeRepository;
import com.assignment.tci.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tci")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

//    //   Just for Testing
//    @GetMapping("/all")
//    public List<Employee> getAllEmployees(){
//        return employeeRepository.findAll();
//    }

    // Requesting payload data and creating Employees in the database
    @PostMapping("/employee-bonus")
    public ResponseEntity<String> saveEmployees(@RequestBody @NonNull EmployeeRequest employeeRequestBody) {
        employeeService.saveEmployees(employeeRequestBody);
        String message = "Employees saved successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


    //    Get mapping takes date from the URL and maps it to String dateStr in the method parameter
    @GetMapping("/employee-bonus")
    public ResponseEntity<BonusEligibleEmployees> getBonusEligibleEmployees(@NonNull @RequestParam("date") String dateStr) {

        // Get the eligible employees grouped by currency
        List<EmployeeCurrencyGroup> eligibleEmployees = employeeService.getBonusEligibleEmployees(dateStr);

        return ResponseEntity.ok(new BonusEligibleEmployees("", eligibleEmployees));

    }

}
