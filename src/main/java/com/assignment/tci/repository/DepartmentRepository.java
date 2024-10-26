package com.assignment.tci.repository;

import com.assignment.tci.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //    @Query("select name from Department d where d.name = :name")
//    Department findByName(@Param("name") String name);
    Department findByName(String name);
}
