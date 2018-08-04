package com.example.ejb;

import javax.ejb.Stateless;
import javax.ejb.Remote;

@Stateless
@Remote
public class HelloBean implements Hello{
  public String sayHello(){
    return "Hello there!";
  }
  
  public String sayGoodbye(){
    return "See ya later!";
  }
}
