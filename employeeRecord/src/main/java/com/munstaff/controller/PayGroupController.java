package com.munstaff.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.munstaff.model.Employee;
import com.munstaff.model.PayGroup;
import com.munstaff.response.Constant.ConstantResponse;
import com.munstaff.response.success.SuccessResponse;
import com.munstaff.services.PayGroupService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class PayGroupController {
  // AN "ENTITY" IS REFERRED TO AS THE PAY-GROUP
  PayGroupService payGroupService;

  @PostMapping("/paygroup")
  public ResponseEntity<?> saveEntity(@RequestBody PayGroup entity, @RequestParam(required = false) Long id) {
    PayGroup newEntity = payGroupService.savePayGroup(entity);
    SuccessResponse successDetails = new SuccessResponse(true, ConstantResponse.SingleRecordResponse,
        ConstantResponse.CreatePayGroupResponse,
        newEntity);
    return new ResponseEntity<>(successDetails, HttpStatus.CREATED);
  };

  @GetMapping("/paygroup/{id}")
  public ResponseEntity<?> getEntity(@PathVariable Long id) {
    PayGroup entity = payGroupService.getPayGroup(id);
    SuccessResponse successDetails = new SuccessResponse(true, ConstantResponse.SingleRecordResponse,
        ConstantResponse.SuccessResponse,
        entity);
    return new ResponseEntity<>(successDetails, HttpStatus.OK);
  };

  @GetMapping("/paygroup/all")
  public ResponseEntity<?> getAllEntity() {
    List<PayGroup> entity = payGroupService.getAllPayGroup();
    SuccessResponse successDetails = new SuccessResponse(true, entity.size(), ConstantResponse.SuccessResponse, entity);
    return new ResponseEntity<>(successDetails, HttpStatus.OK);
  };

  // Get all employees in a payGroup
  @GetMapping("/paygroup/{id}/employee")
  public ResponseEntity<?> getEntityEmployee(@PathVariable Long id) {
    Set<Employee> entityEmployee = payGroupService.getPayGroupEmployee(id);
    SuccessResponse successDetails = new SuccessResponse(true, entityEmployee.size(),
        entityEmployee.isEmpty() ? "No Employee found on this payGroup" : ConstantResponse.SuccessResponse,
        entityEmployee);
    return new ResponseEntity<>(successDetails, HttpStatus.OK);
  };

  @PutMapping("paygroup/{id}")
  public ResponseEntity<?> updateEntity(@PathVariable Long id, @RequestBody PayGroup entity) {
    PayGroup updatedEntity = payGroupService.updatePayGroup(entity, id);
    SuccessResponse successDetails = new SuccessResponse(true, ConstantResponse.SingleRecordResponse,
        ConstantResponse.SuccessResponse,
        updatedEntity);
    return new ResponseEntity<>(successDetails, HttpStatus.OK);
  };

  @PutMapping("paygroup/{payGroupId}/employee/{employeeId}")
  public ResponseEntity<?> addEntityToPayGroup(@PathVariable Long payGroupId, @PathVariable Long employeeId) {
    String entity = payGroupService.addEmployeeToPayGroup(employeeId, payGroupId);
    SuccessResponse successDetails = new SuccessResponse(true, ConstantResponse.SingleRecordResponse,
        ConstantResponse.SuccessResponse,
        entity);
    return new ResponseEntity<>(successDetails, HttpStatus.OK);
  };

  @DeleteMapping("/paygroup/{id}")
  public ResponseEntity<HttpStatus> deleteEntity(@PathVariable Long id) {
    payGroupService.deletePayGroup(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  };

};
