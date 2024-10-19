package com.assignment.tci;

import com.assignment.tci.controller.EmployeeController;
import com.assignment.tci.dto.BonusEligibleResponse;
import com.assignment.tci.dto.EmployeeRequest;
import com.assignment.tci.dto.EmployeeResponse;
import com.assignment.tci.models.Employee;
import com.assignment.tci.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Test
    void testGetBonusEligibleEmployees() throws Exception {
        // Prepare the date and mock the service response
        SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMM-dd-yyyy");
        Date requestedDate = DATE_FORMATTER.parse("may-27-2022");

        BonusEligibleResponse.CurrencyGroup currencyGroup = new BonusEligibleResponse.CurrencyGroup("INR",
                Arrays.asList(new BonusEligibleResponse.EmployeeRequestBonus("raj singh", 5000.0)));

        List<BonusEligibleResponse.CurrencyGroup> currencyGroups = Arrays.asList(currencyGroup);

        when(employeeService.getBonusEligibleEmployees(requestedDate)).thenReturn(currencyGroups);

        // Invoke the getBonusEligibleEmployees method
        ResponseEntity<BonusEligibleResponse> response = employeeController.getBonusEligibleEmployees("may-27-2022");

        // Verify the response and status
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getData().size());
        assertEquals("INR", response.getBody().getData().get(0).getCurrency());

        // Verify that the service method was called once
        verify(employeeService, times(1)).getBonusEligibleEmployees(requestedDate);
    }
}
