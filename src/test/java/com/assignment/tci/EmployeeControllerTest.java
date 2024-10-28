package com.assignment.tci;

import com.assignment.tci.controller.EmployeeController;
import com.assignment.tci.models.BonusEligibleEmployees;
import com.assignment.tci.dto.EmployeeRequest;
import com.assignment.tci.dto.EmployeeResponse;
import com.assignment.tci.models.EmployeeCurrencyGroup;
import com.assignment.tci.models.EmployeeDetails;
import com.assignment.tci.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEmployees() {
        // Prepare sample EmployeeRequest
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmployees(Arrays.asList(
                new EmployeeResponse("raj singh",  5000.0, "INR", "may-20-2022", "may-20-2023","accounts"),
                new EmployeeResponse("pratap m",  3000.0, "INR", "jan-01-2021", "may-20-2023","accounts"),
                new EmployeeResponse("sam",2500.0, "USD", "may-20-2022", "may-20-2023","IT")
        ));

        // Invoke the saveEmployees method
        ResponseEntity<String> response = employeeController.saveEmployees(employeeRequest);

        // Verify the response and status
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Employees saved successfully", response.getBody());

        // Verify that the service method was called once
        verify(employeeService, times(1)).saveEmployees(employeeRequest);
    }


    @ParameterizedTest
    void testGetBonusEligibleEmployees(String dateStr) {

        EmployeeCurrencyGroup employeecurrencyGroup = new EmployeeCurrencyGroup("INR",
                Arrays.asList(new EmployeeDetails("raj singh", 5000.0)));

        List<EmployeeCurrencyGroup> EmployeecurrencyGroups = Arrays.asList(employeecurrencyGroup);

        when(employeeService.getBonusEligibleEmployees(dateStr)).thenReturn(EmployeecurrencyGroups);

        // Invoke the getBonusEligibleEmployees method
        ResponseEntity<BonusEligibleEmployees> response = employeeController.getBonusEligibleEmployees("may-27-2022");

        // Verify the response and status
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getData().size());
        assertEquals("INR", response.getBody().getData().get(0).getCurrency());

        // Verify that the service method was called once
        verify(employeeService, times(1)).getBonusEligibleEmployees(dateStr);
    }
}
