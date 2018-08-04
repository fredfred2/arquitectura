package com.example.ejb;

import javax.ejb.Remote;

@Remote
public interface Hello {
    public String sayHello();
    public String sayGoodbye();   
}
