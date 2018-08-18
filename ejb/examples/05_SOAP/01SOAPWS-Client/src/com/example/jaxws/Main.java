/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jaxws;

/**
 *
 * @author oracle
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        GreeterService service = new GreeterService();
        Greeter port = service.getGreeterPort();
        System.out.println("The Message is: " + port.greetWorld());
    }

}
