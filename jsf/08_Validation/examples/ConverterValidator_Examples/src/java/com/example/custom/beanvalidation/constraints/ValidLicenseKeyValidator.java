package com.example.custom.beanvalidation.constraints;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidLicenseKeyValidator implements ConstraintValidator<ValidLicenseKey, String> {
    private Map<String, String> prodLicenseKey;
    
    @Override
    public void initialize(ValidLicenseKey constraintAnnotation) {
        // These could come from a properties file or better, a database
        prodLicenseKey = new HashMap();
        prodLicenseKey.put("XRP", "[a-z]*"); // XRP-xxxxxx 
        prodLicenseKey.put("RWS", "[1-9]*"); // RWS-nnnnnn   
    }

    @Override
    public boolean isValid(String licenseKey, ConstraintValidatorContext context) {
        // break the string passed in into the product code and license key components
        String[] keyComponents = licenseKey.split("\\s*-\\s*");
        
        // If the number of keyComponents is any number other than two then fail
        if (keyComponents.length != 2) {
            return false;
        }
        
        // Check if the character count for the key and value is correct - it must be
        // 3 characters for the product code and 6 characters for the license key
        if (keyComponents[0].length() != 3 || keyComponents[1].length() !=6) {
            return false;
        }
        
        // Now check if the product code exists as a key in the Map
        if (!prodLicenseKey.containsKey(keyComponents[0])) {
            return false;
        }
        
        // Now check if the license meets the pattern - actual validation of the
        // license key itself would be an additional step
        if (!(Pattern.matches((String)prodLicenseKey.get(keyComponents[0]),keyComponents[1]))){
            return false;
        }
        
        // Everything passed!
        return true;
    }
}