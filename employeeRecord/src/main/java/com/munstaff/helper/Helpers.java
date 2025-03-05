package com.munstaff.helper;

import java.math.BigDecimal;
import java.math.*;

public class Helpers {
  public static BigDecimal generateGrossPay(BigDecimal basic, BigDecimal housing, BigDecimal transport,
      BigDecimal utility) {
    BigDecimal totalGrossPay = basic.add(housing).add(transport).add(utility);

    return totalGrossPay;
  };

  public static BigDecimal generateEmployeePension(BigDecimal basic, BigDecimal housing, BigDecimal transport) {
    BigDecimal totalGrossPay = basic.add(housing).add(transport);
    BigDecimal multiplier = BigDecimal.valueOf(Variables.EMPLOYEE_PENSION_RATE);

    BigDecimal employeePension = totalGrossPay.multiply(multiplier).setScale(2, RoundingMode.UP);

    return employeePension;
  };

  public static BigDecimal generateEmployerPension(BigDecimal basic, BigDecimal housing, BigDecimal transport) {
    BigDecimal totalGrossPay = basic.add(housing).add(transport);
    BigDecimal multiplier = BigDecimal.valueOf(Variables.EMPLOYER_PENSION_RATE);

    BigDecimal employerPension = totalGrossPay.multiply(multiplier).setScale(2, RoundingMode.UP);

    return employerPension;
  };

  public static BigDecimal generateNetPay(BigDecimal grossPay, BigDecimal tax, BigDecimal pension) {
    BigDecimal totalNetPay = grossPay.subtract(tax).subtract(pension);

    return totalNetPay;
  };
};