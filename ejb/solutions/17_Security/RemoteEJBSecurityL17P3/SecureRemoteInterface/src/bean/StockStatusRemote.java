/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Remote;

/**
 *
 * @author root
 */
@Remote
public interface StockStatusRemote {

    String getStatus();
    
}
