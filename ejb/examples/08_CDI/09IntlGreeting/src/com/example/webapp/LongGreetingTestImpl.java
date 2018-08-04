package com.example.webapp;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

@RequestScoped
@Alternative
public class LongGreetingTestImpl implements LongGreeting{
    @Override
    public String get(){
        return "This is the alternative long greeting: hello!";
    }
}
