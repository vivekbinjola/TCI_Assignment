package com.assignment.tci.repository;

import com.assignment.tci.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

}
