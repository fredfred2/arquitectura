package com.example.webapp;

public class MessageOne implements Message{
  public MessageOne(){}
  
  @Override
  public String get(){
    return "A message from MessageOne";
  }
  
}
