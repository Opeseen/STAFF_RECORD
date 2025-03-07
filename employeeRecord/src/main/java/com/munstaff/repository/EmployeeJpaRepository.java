package com.munstaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munstaff.entity.Employee;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

};
