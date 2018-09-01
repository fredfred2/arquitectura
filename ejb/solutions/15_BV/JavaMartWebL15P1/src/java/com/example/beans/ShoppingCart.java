package com.example.beans;

import com.example.dao.SalesDAO;
import com.example.dao.InventoryDAO;
import com.example.dao.ProductDAO;
import com.example.dao.ProductSalesDAO;
import com.example.model.Inventory;
import com.example.model.Product;
import com.example.model.ProductSales;
import com.example.model.Sales;
import com.example.util.PurchaseException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@SessionScoped
public class ShoppingCart implements Serializable {

    private static final Logger logger = Logger.getLogger("com.example.beans.ShoppingCart");

    @Inject
    private ProductDAO productDAO;
    @Inject
    private InventoryDAO inventoryDAO;
    @Inject
    private ProductSalesDAO productSalesDAO;
    @Inject
    private SalesDAO salesDAO;

    //Transaction context
//    @Resource
//    private UserTransaction ut;
    private Map<Product, Integer> cart;

    // Create a Shopping Cart post construct
    public ShoppingCart() {
        cart = new HashMap<>();
    }

    /*
     * Add and item to the cart
     */
    public void addItem(String product_id, int quantity) {
        try {
            Product item = productDAO.getProductById(product_id);
            /* This lambda expression increments the value of the item in the cart or adds a new one
             * cart.merge(item, quantity, (value, newValue) -> value += newValue);
             */

            // Does this product already exist in the cart? If so, simply increment the count
            // if not, create the entry
            if (cart.containsKey(item)) {
                int currQuantity = cart.get(item);
                currQuantity += quantity;
                cart.put(item, currQuantity);
            } else {
                cart.put(item, quantity);
            }
        } catch (SQLException ex) {
            System.out.println("CartBean: addItem: " + ex);
        }
    }

    /*
     * Remove an item from the card
     */
    public void removeItem(Product item) {
        //shoppingCart.removeItem(item);
        cart.remove(item);
    }

    /* 
     * Get the count of cart items
     */
    public int getItemCount() {
        /* lambda expression to replace below
         * count = products.stream().map((p) -> cart.get(p)).reduce(count, Integer::sum);
         */
        int count = 0;
        Set<Product> products = cart.keySet();
        for (Product p : products) {
            count += cart.get(p);
        }
        return count;
    }

    /*
     * Return the current cart
     */
    public Map<Product, Integer> getCart() {
        return cart;
    }

    /*
     * purchaseCart
     * This method takes the shopping cart object stored in stateful session state and "purchases" it,
     * converting it into a set of ProductSales records and matching Sales record.
     * Once the method completes, (successfully or not) the session state is destroyed.
     */
    @Transactional(value = Transactional.TxType.REQUIRED,
                   rollbackOn = {PurchaseException.class})
    public Sales purchaseCart() throws PurchaseException {
        Sales sales = null;
        try {
            // Create a new Sales object
            sales = salesDAO.createSalesRecord();
            // Get the list of Products in the cart
            Set<Product> products = cart.keySet();
            // Keep track of the total order cost
            double totalCost = 0.0;
            // Attempt to add each product to Sales order
            for (Product product : products) {
                // Get the quantity desired of this product item
                int quantity = cart.get(product);
                // Get the inventory object for this Product
                Inventory inventory = inventoryDAO.getInventory(product.getId());
                // Check that there is sufficient quantity to sell
                if (!inventory.subtract(quantity)) {
                    // Rollback this transaction
                    // Log the error:
                    String error = "Unable to purchase: " + product.getId()
                            + " : Desired quantity: " + quantity + " :  Available quantity: "
                            + inventory.getQuantity_on_hand();
                    logger.log(Level.INFO, error);
                    throw new PurchaseException(error);
                } else {
                    // Add this to the total cost of the order
                    totalCost += product.getPrice() * quantity;
                    // Create a ProductSales object
                    ProductSales productSales = new ProductSales(sales.getSales_id(), product.getId(), quantity);
                    // Save it to the database
                    productSalesDAO.addProductSalesRecord(productSales);
                    // Update the inventory
                    inventoryDAO.updateInventory(product.getId(), inventory.getQuantity_on_hand());
                }
            }
            // Update the Sales object with the total order and the date
            sales.setSales_date(new Date());
            sales.setTotal_sale(totalCost);
            salesDAO.updateSalesRecord(sales);
            // Commit the transaction
        } catch (SQLException ex) {
            System.out.println("ShoppingCart: purchaseCart: " + ex);
            throw new PurchaseException("SQLException in purchaseCart: " + ex);
        } finally {
            // destroy the current shopping cart
            cart = new HashMap<>();
        }
        return sales;
    }

    /*
     * Reset this cart
     *
     */
    public void resetCart() {
        cart = new HashMap<>();
    }
}
