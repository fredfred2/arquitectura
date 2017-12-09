package com.example.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    @Override
    public void initialize(ZipCode constraintAnnotation) {
        // Here you would access a zip code database perhaps
    }

    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext context) {
        // Check for null and empty string
        if (zipCode == null || zipCode.length() <= 0) {
            return false;
        }
        
        // Check the length, only 5 or 10 are acceptable
        if (zipCode.length() != 5 && zipCode.length() != 10) {
            return false;
        }
        
        // If length is 10, split the string into two tokens at the dash
        if (zipCode.length() == 10) {
             String[] token = zipCode.split("\\s*-\\s*");
             
             if (token.length != 2) {
                 return false;
             }
             
             // Check the length of the second string
             if (token[1].length() != 4) {
                 return false;
             }
             
             // Check both strings are digits only
             if (!token[0].matches("[0-9]{5}") || !token[1].matches("[0-9]{4}")) {
                 return false;
             }
        } else {
            // test the 5 digit string for digits only
            if(!zipCode.matches("[0-9]{5}")) {
                return false;
            }
        }
        
        // Success!
        return true;
    }
}
