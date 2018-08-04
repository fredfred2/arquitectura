package com.example.webapp;

import javax.enterprise.context.RequestScoped;
import java.util.Random;
import javax.enterprise.inject.Produces;

@RequestScoped
@SecondMessage
public class MessageTwo implements Message{
  private Random random = new Random(System.currentTimeMillis());
  
  public MessageTwo(){}
  
  @Override
  public String get(){
    return "Message from CDI MessageTwo.";
  }
  
  public Random getRandom(){
    return random;
  }
  
  @Produces
  @RandomNum
  public int getNext(){
    return this.getRandom().nextInt(100);
  }
}
