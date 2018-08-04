package com.example.webapp;

import javax.enterprise.context.RequestScoped;
import java.util.Random;

@RequestScoped @Spanish
public class SpanishGreeting implements Greeting{
  private Random random = new Random(System.currentTimeMillis());
  
  public SpanishGreeting(){}
  
  @Override
  public String get(){
    return "Hola amigo!";
  }
}
