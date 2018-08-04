package com.example.webapp;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MessageOne implements Message{
  public MessageOne(){}
  
  @Override
  public String get(){
    return "Message from CDI MessageOne";
  }
}
