package com.example.webapp;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;

@SessionScoped
public class ItemEventHandler implements Serializable{
  private static final Logger logger = Logger.getLogger("Cart Transactions");
  
  public void addItem(@Observes @AddItem Item newItem){
    logger.log(Level.INFO, "CartHandler - Add Item: " + newItem.toString() );    
  }
  
  public void removeItem(@Observes @RemoveItem Item newItem){
    logger.log(Level.INFO, "CartHandler - Remove Item: " + newItem.toString());    
  }
}
