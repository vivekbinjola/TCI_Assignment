package com.assignment.tci;

import com.assignment.tci.dto.BonusEligibleResponse;
import com.assignment.tci.models.Department;
import com.assignment.tci.models.Employee;
import com.assignment.tci.repository.DepartmentRepository;
import com.assignment.tci.repository.EmployeeRepository;
import com.assignment.tci.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBonusEligibleEmployees() throws Exception {
        // Prepare sample data
        SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMM-dd-yyyy");
        Date requestedDate = DATE_FORMATTER.parse("may-27-2022");

        Department department1 = new Department(1L, "accounts");
        Department department2 = new Department(2L, "IT");

        Employee emp1 = new Employee(null, "raj singh", 5000.0, "INR",
                DATE_FORMATTER.parse("may-20-2022"), DATE_FORMATTER.parse("may-20-2023"), department1);

        Employee emp2 = new Employee(null, "pratap m", 3000.0, "INR",
                DATE_FORMATTER.parse("jan-01-2021"), DATE_FORMATTER.parse("may-20-2023"), department1);

        Employee emp3 = new Employee(null, "sam", 2500.0, "USD",
                DATE_FORMATTER.parse("may-20-2022"), DATE_FORMATTER.parse("may-20-2023"), department2);

        List<Employee> employees = Arrays.asList(emp1, emp2, emp3);

        // Mock repository response
        when(employeeRepository.findAll()).thenReturn(employees);

        // Invoke service method
        List<BonusEligibleResponse.CurrencyGroup> result = employeeService.getBonusEligibleEmployees(requestedDate);

        // Sort the results for consistent order
        result.sort(Comparator.comparing(BonusEligibleResponse.CurrencyGroup::getCurrency));

        // Debugging: print employee names
        for (BonusEligibleResponse.CurrencyGroup currencyGroup : result) {
            System.out.println("Currency: " + currencyGroup.getCurrency());
            currencyGroup.getEmployees().forEach(emp -> System.out.println("Employee: " + emp.getEmpName()));
        }

        // Verify the result
        assertEquals(2, result.size());

        BonusEligibleResponse.CurrencyGroup inrGroup = result.get(0);
        BonusEligibleResponse.CurrencyGroup usdGroup = result.get(1);

        assertEquals("INR", inrGroup.getCurrency());
        assertEquals(2, inrGroup.getEmployees().size());

        // Check employee names after sorting
        assertEquals("pratap m", inrGroup.getEmployees().get(0).getEmpName());
        assertEquals("raj singh", inrGroup.getEmployees().get(1).getEmpName());

        assertEquals("USD", usdGroup.getCurrency());
        assertEquals(1, usdGroup.getEmployees().size());
        assertEquals("sam", usdGroup.getEmployees().get(0).getEmpName());

        // Verify that repository method was called once
        verify(employeeRepository, times(1)).findAll();
    }


}
