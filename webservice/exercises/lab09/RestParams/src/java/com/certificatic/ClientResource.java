/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certificatic;

import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author admin
 */
@Path("clients")
public class ClientResource {
    @PUT
    @Consumes({MediaType.APPLICATION_XML})
    public void updateClient(Client client){
        System.out.println("Client:"
                +client.getName()
                +", id:"
                +client.getClientId());
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Client insertClient(Client client){
        client.setClientId(new Random().nextInt());
        System.out.println("Client:"
                +client.getName()
                +", id:"
                +client.getClientId());
        return client;
    }
    
}
