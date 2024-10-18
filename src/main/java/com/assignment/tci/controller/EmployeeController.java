package com.assignment.tci.controller;
import com.assignment.tci.dto.BonusEligibleResponse;
import com.assignment.tci.dto.EmployeeDTO;
import com.assignment.tci.dto.EmployeeRequest;
import com.assignment.tci.models.Employee;
import com.assignment.tci.repository.EmployeeRepository;
import com.assignment.tci.service.EmployeeService;
import lombok.SneakyThrows;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tci")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    //    Just for Testing
    @GetMapping("/all")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

// Requesting payload data and creating Employees in the database
    @PostMapping("/employee-bonus")
    public ResponseEntity<String> saveEmployees(@RequestBody @NonNull EmployeeRequest employeeRequestBody) {
        employeeService.saveEmployees(employeeRequestBody);
        String message = "Employees saved successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


//    Still working on it
    @GetMapping("/tci/employee-bonus")
    public ResponseEntity<BonusEligibleResponse> getBonusEligibleEmployees(@RequestParam("date") String dateStr) {
        try {
            // Parse the input date
            SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMM-dd-yyyy");
            Date requestedDate = DATE_FORMATTER.parse(dateStr);
            System.out.println(requestedDate);

            // Get the eligible employees grouped by currency
            List<BonusEligibleResponse.CurrencyGroup> eligibleEmployees = employeeService.getBonusEligibleEmployees(requestedDate);

            // Return the response
            return ResponseEntity.ok(new BonusEligibleResponse("", eligibleEmployees));

        } catch (DateTimeParseException e) {
            // Return an error message if date parsing fails
            return ResponseEntity.badRequest().body(new BonusEligibleResponse("Invalid date format", null));
        } catch (Exception e) {
            // Generic error handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BonusEligibleResponse("Internal server error", null));
        }
    }

}
