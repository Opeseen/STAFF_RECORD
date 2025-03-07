package com.munstaff.services;

import java.util.List;
import java.util.Set;

import com.munstaff.entity.Employee;
import com.munstaff.entity.PayGroup;

public interface PayGroupService {
  PayGroup savePayGroup(PayGroup payGroup);

  PayGroup getPayGroup(Long id);

  Set<Employee> getPayGroupEmployee(Long id);

  PayGroup updatePayGroup(PayGroup payGroup, Long id);

  List<PayGroup> getAllPayGroup();

  void deletePayGroup(Long id);

  String addEmployeeToPayGroup(Long employeeId, Long payGroupId);
};