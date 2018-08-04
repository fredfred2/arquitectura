package com.example;

import java.util.Date;
import javax.ejb.Stateless;

@Stateless
public class TimeSessionBean implements TimeSessionBeanRemote {

    @Override
    public Date getDate() {
        return new Date();
    }
    
}