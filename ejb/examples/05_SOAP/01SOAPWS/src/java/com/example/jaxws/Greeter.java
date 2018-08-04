package com.example.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Greeter {
  public final String worldGreeting = "Hello World!";
  
  public Greeter(){ }
  
  @WebMethod
  public String greetWorld(){
    return worldGreeting;
  }  
}
