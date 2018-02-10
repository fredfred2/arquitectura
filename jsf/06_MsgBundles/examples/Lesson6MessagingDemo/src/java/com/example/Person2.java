package com.example;

import javax.faces.bean.*;

@ManagedBean
public class Person2 extends Person {
  @Override
  public String doRegistration() {
    return("success2");
  }
}
