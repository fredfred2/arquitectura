/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 *
 * @author root
 */
@Stateless
public class StockStatus implements StockStatusRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private String items = "500";

    @Override
    @RolesAllowed({"USERS"})
    public String getStatus() {
        return "The stock contains " + items + " items";
    }
    
}
