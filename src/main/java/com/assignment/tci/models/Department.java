package com.assignment.tci.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString(exclude = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // One-to-many relationship with employees

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private List<Employee> employees;

    public Department(long id, String name) {
        this.id = id;
        this.name = name;
    }
}