package com.example.webapp;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logging
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class GreetingInterceptor {
    
  @AroundInvoke
  public Object logBeforeInvoke(InvocationContext context) throws Exception{
    System.out.println("Invoked Greeting for a class named " + context.getClass().getName()
            + " and for a method named " + context.getMethod().getName());   
      return context.proceed();
  }    
}
