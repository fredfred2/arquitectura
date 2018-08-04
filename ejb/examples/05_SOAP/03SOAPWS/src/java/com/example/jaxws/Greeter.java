package com.example.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class Greeter {
  public final String englishGreeting = "Hello ";
  
  public Greeter(){ }
  
  @WebMethod(operationName="GreetFirst")
  public String greetName(@WebParam(name="firstName") String name){
    return englishGreeting + name;
  }
 
  @WebMethod(operationName="GreetFull")
  public String greetFullName(
          @WebParam(name="firstName") String first, 
          @WebParam(name="lastName") String last){
    return englishGreeting + first + " " + last;
  }
}
