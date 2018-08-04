package com.example.webapp;

import javax.enterprise.context.RequestScoped;

@RequestScoped @French
public class FrenchGreeting implements Greeting{
  public FrenchGreeting(){}
  
  @Override
  public String get(){
    return "Bonjour dude!";
  }
}
