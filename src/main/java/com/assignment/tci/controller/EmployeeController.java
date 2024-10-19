package com.assignment.tci.controller;
import com.assignment.tci.dto.BonusEligibleResponse;
import com.assignment.tci.dto.EmployeeRequest;
import com.assignment.tci.models.Employee;
import com.assignment.tci.repository.EmployeeRepository;
import com.assignment.tci.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public ResponseEntity<BonusEligibleResponse> getBonusEligibleEmployees(@NonNull @RequestParam("date") String dateStr) throws ParseException {

    // Parse the input date
    SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMM-dd-yyyy");
    Date requestedDate = DATE_FORMATTER.parse(dateStr);
    System.out.println(requestedDate);

    // Get the eligible employees grouped by currency
    List<BonusEligibleResponse.CurrencyGroup> eligibleEmployees = employeeService.getBonusEligibleEmployees(requestedDate);

    return ResponseEntity.ok(new BonusEligibleResponse("", eligibleEmployees));

    }

}
