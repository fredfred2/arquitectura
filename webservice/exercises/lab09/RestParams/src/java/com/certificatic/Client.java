/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certificatic;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@XmlRootElement(name = "clientezote")
public class Client {
 
    private String name;
    private Integer clientId;

    public Integer getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

}
