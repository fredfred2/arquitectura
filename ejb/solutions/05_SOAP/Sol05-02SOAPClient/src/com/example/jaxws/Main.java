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

        CalcService serv = new CalcService();
        Calc servPort = serv.getCalcPort();

        System.out.println("10 + 9 is: " + servPort.add("10", "9"));
        System.out.println("10 - 9 is: " + servPort.subtract("10", "9"));
        System.out.println("10 squared is: " + servPort.squared("10"));
    }

}
