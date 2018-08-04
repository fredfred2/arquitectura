package com.example.jaxws;

public class Main {
  public static void main(String[] args) {
    GreeterService service = new GreeterService();
    Greeter port = service.getGreeterPort();
    System.out.println("The Message is: " + port.greetWorld()); 
  }
}