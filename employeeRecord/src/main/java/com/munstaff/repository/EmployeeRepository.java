package com.munstaff.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.munstaff.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  Boolean existsByEmail(String email);
};
