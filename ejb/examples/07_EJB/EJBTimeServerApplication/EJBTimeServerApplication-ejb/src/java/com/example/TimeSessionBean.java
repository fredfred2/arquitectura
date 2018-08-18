/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author oracle
 */
@Stateless
public class TimeSessionBean implements TimeSessionBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Date getDate() {
        return new Date();
    }
    
}
