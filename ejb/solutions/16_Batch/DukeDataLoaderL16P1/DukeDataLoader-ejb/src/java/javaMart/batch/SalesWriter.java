/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaMart.batch;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.sql.DataSource;
/**
 *
 * @author oracle
 */

@Named
public class SalesWriter extends AbstractItemWriter{
    @Resource(lookup = "jdbc/JavaMartDB")
private DataSource dataSource;
    private Connection connection;
    private PreparedStatement insertItem;
    private PreparedStatement insertSale;
    
@Override
public void open(Serializable checkpoint) throws Exception {
  connection = dataSource.getConnection();
  insertSale = connection.prepareStatement("INSERT INTO Sales( Date_Sold, Total_Sale) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
  insertItem = connection.prepareStatement("INSERT INTO ProductSales(Sales_ID, Product_ID, Quantity) VALUES(?,?,?)");
}

@Override
public void writeItems(List<Object> items) throws Exception {
  for (Object o : items) {
    Sale sale = (Sale) o;
    long saleId = insert(sale);
    for (Item item : sale.getItems()) {
      insert(saleId, item);
    }
  }
}
    


private long insert(Sale sale) throws SQLException {
    insertSale.setTimestamp(1, new Timestamp(sale.getDate().getTime()));
    insertSale.setDouble(2, sale.getTotal());
    insertSale.executeUpdate();
    ResultSet generatedKeys = insertSale.getGeneratedKeys();
    generatedKeys.next();
    return generatedKeys.getLong(1);
}

private void insert(long saleId, Item item) throws SQLException {
    insertItem.setLong(1, saleId);
    insertItem.setString(2, item.getId());
    insertItem.setInt(3, item.getQuantity());
    insertItem.executeUpdate();
}
@Override
public void close() throws Exception {
    insertSale.close();
    insertItem.close();
    connection.close();
}
}
