package com.example.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Calc {
  
  @WebMethod
  public String squared(String number){
    double num = Double.valueOf(number);
    return Double.toString(num * num);
  }  
  
  @WebMethod(operationName="add")
  public String addTwo(String aStr, String bStr){
    double a = Double.valueOf(aStr);
    double b = Double.valueOf(bStr);
    return Double.toString(a + b);    
  }

  @WebMethod(operationName="subtract")
  public String subtractTwo(String aStr, String bStr){
    double a = Double.valueOf(aStr);
    double b = Double.valueOf(bStr);
    return Double.toString(a - b);    
  }
}
