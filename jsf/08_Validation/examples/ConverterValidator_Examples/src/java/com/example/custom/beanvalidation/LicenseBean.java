package com.example.custom.beanvalidation;

import com.example.custom.beanvalidation.constraints.ValidLicenseKey;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LicenseBean {

    @ValidLicenseKey
    private String licenseKey;

    public LicenseBean() {
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public String validateLicense() {
        return "success";
    }
}