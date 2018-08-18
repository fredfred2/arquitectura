/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javax.ejb.Remote;

/**
 *
 * @author oracle
 */
@Remote
public interface CalculatorBeanRemote {

    int add(int a, int b);

    int subtract(int a, int b);
    
}
