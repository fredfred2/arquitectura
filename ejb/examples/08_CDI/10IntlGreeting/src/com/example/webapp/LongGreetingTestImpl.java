package com.example.webapp;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LongGreetingTestImpl implements LongGreeting{
    @Override
    public String get(){
        return "This is the alternative long greeting: hello!";
    }
}
