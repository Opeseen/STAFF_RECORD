package com.munstaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munstaff.model.Employee;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

};
