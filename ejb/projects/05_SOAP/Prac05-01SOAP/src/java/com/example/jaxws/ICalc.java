/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jaxws;

import javax.ejb.Local;

/**
 *
 * @author fredfred2
 */
@Local
public interface ICalc {
    String squared(String number) throws EmptyStringException;
}
