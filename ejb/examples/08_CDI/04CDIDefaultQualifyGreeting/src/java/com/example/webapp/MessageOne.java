package com.example.webapp;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Named;

@Named
@RequestScoped
@Default
@Any
public class MessageOne implements Message{
  public MessageOne(){}
  
  @Override
  public String get(){
    return "Message from CDI MessageOne";
  }
}
