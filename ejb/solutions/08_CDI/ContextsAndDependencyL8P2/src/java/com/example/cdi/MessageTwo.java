/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.cdi;

import javax.enterprise.context.RequestScoped;

/**
 *
 * @author oracle
 */
@RequestScoped @SecondMessage
public class MessageTwo implements Message{
    public MessageTwo(){}
    
    @Override
    public String get() {
        return "Message from CDI MessageTwo";
    }
    
}
