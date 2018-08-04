package com.example.webapp;

import javax.enterprise.context.RequestScoped;
import java.util.Random;

@RequestScoped @Italian
public class ItalianGreeting implements Greeting{
  private Random random = new Random(System.currentTimeMillis());
  
  public ItalianGreeting(){}
  
  @Override
  public String get(){
    return "Ciao baby!";
  }
}
