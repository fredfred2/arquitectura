package com.example.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Greeter {
  public final String englishGreeting = "Hello ";
  
  public Greeter(){ }
  
  @WebMethod(operationName="GreetFirst")
  public String greetName(String name){
    return englishGreeting + name;
  }
 
  @WebMethod(operationName="GreetFull")
  public String greetFullName(String first, String last){
    return englishGreeting + first + " " + last;
  }
}
