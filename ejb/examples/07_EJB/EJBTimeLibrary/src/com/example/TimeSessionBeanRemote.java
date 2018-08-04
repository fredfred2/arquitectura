package com.example;

import java.util.Date;
import javax.ejb.Remote;

@Remote
public interface TimeSessionBeanRemote {

    Date getDate();
    
}