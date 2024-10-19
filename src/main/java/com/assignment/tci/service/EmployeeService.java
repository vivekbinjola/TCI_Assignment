package com.assignment.tci.service;
import com.assignment.tci.dto.BonusEligibleResponse;
import com.assignment.tci.dto.EmployeeRequest;
import com.assignment.tci.models.Department;
import com.assignment.tci.models.Employee;
import com.assignment.tci.repository.DepartmentRepository;
import com.assignment.tci.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

//    Saving employees data in the employee table and departmentName in the department table using JPA functions.
    public void saveEmployees(EmployeeRequest employeeRequestBody) {
//        Fetching Individual Employees
        employeeRequestBody.getEmployees().stream().forEach(employee -> {
            log.info("Entered ForEach");
            Department department = departmentRepository.findByName(employee.getDepartment());

//            If department does not exist in the department table, only then save it.
            if (department == null) {
                department = new Department();
                department.setName(employee.getDepartment());
                departmentRepository.save(department);
            }
//            Formatting String date from the URL in the required format
            SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMM-dd-yyyy");
//          Creating and storing new employees using Employee Object
            Employee employee1 = new Employee();
            employee1.setDepartment(department);
            employee1.setAmount(employee.getAmount());
            employee1.setCurrency(employee.getCurrency());
            try{
            employee1.setExitDate( DATE_FORMATTER.parse(employee.getExitDate()));
            employee1.setJoiningDate((DATE_FORMATTER.parse(employee.getJoiningDate())));
            }catch(ParseException e){
                log.info(String.valueOf(e.getStackTrace()));
            }
            // Saving Employees Data
            employee1.setEmpName(employee.getEmpName());
            employeeRepository.save(employee1);
        });

    }

    public List<BonusEligibleResponse.CurrencyGroup> getBonusEligibleEmployees(Date requestedDate) {
//       Taking all Employees from the DB
        List<Employee> allEmployees = employeeRepository.findAll();

        // step 1 : Filtering employees by the provided date and sorting by employee names
        List<Employee> eligibleEmployees = allEmployees.stream()
                .filter(emp -> emp.getJoiningDate().before(requestedDate) && emp.getExitDate().after(requestedDate))
                .sorted(Comparator.comparing(Employee::getEmpName))
                .collect(Collectors.toList());

        // step 2 : Grouping employees by currency
        Map<String, List<Employee>> groupedByCurrency = eligibleEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getCurrency));

        // sending the response in the requested format
        return groupedByCurrency.entrySet().stream()
                .map(entry -> new BonusEligibleResponse.CurrencyGroup(entry.getKey(),
                        entry.getValue().stream()
                                .map(emp -> new BonusEligibleResponse.EmployeeRequestBonus(emp.getEmpName(), emp.getAmount()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}

