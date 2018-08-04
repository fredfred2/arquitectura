package com.example.webapp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MessageOne implements Message{
  public MessageOne(){}
  
  @Override
  public String get(){
    return "Message from CDI MessageOne";
  }
}
