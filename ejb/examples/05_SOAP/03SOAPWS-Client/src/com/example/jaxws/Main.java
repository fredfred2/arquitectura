package com.example.jaxws;

public class Main {
  public static void main(String[] args) {
    GreeterService service = new GreeterService();
    Greeter port = service.getGreeterPort();
    
    System.out.println("First name greeting: " + port.greetFirst("Bob"));
    System.out.println("Full name greeting: " + port.greetFull("Bob", "Smith"));
  } 
}
