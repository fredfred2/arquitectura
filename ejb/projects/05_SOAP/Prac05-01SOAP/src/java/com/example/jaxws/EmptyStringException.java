/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jaxws;

/**
 *
 * @author fredfred2
 */
public class EmptyStringException extends Exception {

    public EmptyStringException() {
    }

    EmptyStringException(String esta_mal_tu_cadena) {
        super(esta_mal_tu_cadena);
    }
    
}
