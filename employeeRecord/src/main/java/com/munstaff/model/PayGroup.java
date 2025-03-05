package com.munstaff.model;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "paygroup")
public class PayGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column(unique = true)
  private String category;
  @NonNull
  private BigDecimal basic;
  @NonNull
  private BigDecimal housing;
  @NonNull
  private BigDecimal transport;
  @NonNull
  private BigDecimal utility;
  @NonNull
  private BigDecimal grossPay;
  private BigDecimal tax;
  private BigDecimal employeePensionContribution;
  private BigDecimal employerPensionContribution;
  private BigDecimal netPay;

  @JsonIgnore
  @OneToMany
  @JoinTable(name = "employee_paygroup", joinColumns = @JoinColumn(name = "paygroup_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
  private Set<Employee> employee;
};
