/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaMart.batch;

/**
 *
 * @author oracle
 */

import java.util.Date;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import javax.json.JsonObject;
import javax.json.JsonValue;


@Named
public class SalesProcessor implements ItemProcessor {
    @Override
public Object processItem(Object item) throws Exception {
    JsonObject jsonSale = (JsonObject) item;
    Sale sale = new Sale();
    sale.setDate(new Date(jsonSale.getJsonNumber("timestamp").longValue()));
    sale.setStatus(jsonSale.getInt("status"));
    sale.setTotal(jsonSale.getJsonNumber("total").doubleValue());
    
    for (JsonValue jsonItemVal : jsonSale.getJsonArray("items")) {
  JsonObject jsonItem = (JsonObject) jsonItemVal;
  Item saleItem = new Item();
  saleItem.setId(jsonItem.getString("productId"));
  saleItem.setQuantity(jsonItem.getInt("productCount"));
  sale.getItems().add(saleItem);
}
    return sale;
}
    
}
