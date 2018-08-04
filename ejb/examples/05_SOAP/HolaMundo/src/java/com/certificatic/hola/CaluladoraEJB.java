/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certificatic.hola;

import javax.ejb.Stateless;

/**
 *
 * @author fredfred2
 */
@Stateless
public class CaluladoraEJB {
       public int suma(int a,int b){
           return a+b;
       }
}
