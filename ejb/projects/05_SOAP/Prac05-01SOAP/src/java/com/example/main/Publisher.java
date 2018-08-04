/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.main;

import com.example.jaxws.Calc;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.xml.ws.Endpoint;

/**
 *
 * @author fredfred2
 */
public class Publisher {

    public static void main(String[] args) {
        Endpoint endpoint = Endpoint.create(new Calc());
        int pool = 100;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(pool, pool, 5, TimeUnit.SECONDS, 
                new LinkedBlockingDeque<Runnable>(pool));
        endpoint.setExecutor(executor);
        endpoint.publish("http://localhost:9876/CalcMain");
    }
}
