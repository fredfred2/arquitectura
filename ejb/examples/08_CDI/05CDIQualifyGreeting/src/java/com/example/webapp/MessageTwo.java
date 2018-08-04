package com.example.webapp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
@SecondMessage
public class MessageTwo implements Message{
  public MessageTwo(){}
  
  @Override
  public String get(){
    return "Message from CDI MessageTwo";
  }
}
