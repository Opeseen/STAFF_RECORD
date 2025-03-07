package com.munstaff.services;

import java.util.List;

import com.munstaff.entity.Employee;
import com.munstaff.entity.PayGroup;

public interface EmployeeService {
  Employee saveEmployee(Employee staff);

  Employee updateEmployee(Long id, Employee staff);

  Employee getEmployee(Long id);

  PayGroup getEmployeePayGroup(Long id);

  Employee updateEmployeePayGroup(Long employeeId, Long payGroupId);

  List<Employee> getAllEmployee();

  void deleteEmployee(Long id);
}
