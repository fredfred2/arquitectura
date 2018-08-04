package com.example.webapp;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@RequestScoped
public class LongGreetingImpl implements LongGreeting{
    public String get(){
        return "This is the long form greeting: hello!";
    }
}
