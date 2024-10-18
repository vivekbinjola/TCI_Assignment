package com.assignment.tci;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.assignment.tci.dto.EmployeeDTO;
import com.assignment.tci.dto.EmployeeRequest;
import com.assignment.tci.models.Department;
import com.assignment.tci.models.Employee;
import com.assignment.tci.dto.EmployeeRequest;
import com.assignment.tci.repository.DepartmentRepository;
import com.assignment.tci.repository.EmployeeRepository;
import com.assignment.tci.repository.DepartmentRepository;
import com.assignment.tci.repository.EmployeeRepository;
import com.assignment.tci.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployees() throws ParseException {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmployees(Arrays.asList(
            new EmployeeDTO("raj singh",  5000.0, "INR", "may-20-2023", "may-20-2023","accounts")
        ));

        // Mocking the department repository
        Department mockDepartment = new Department();
        mockDepartment.setName("accounts");
        when(departmentRepository.findByName("accounts")).thenReturn(null); // Simulating that department does not exist
        when(departmentRepository.save(any(Department.class))).thenReturn(mockDepartment);

        // Mocking employee repository save
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());

        // Call the method under test
        employeeService.saveEmployees(request);

        // Verify interactions
        verify(departmentRepository, times(1)).findByName("accounts");
        verify(departmentRepository, times(1)).save(any(Department.class));
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
}
