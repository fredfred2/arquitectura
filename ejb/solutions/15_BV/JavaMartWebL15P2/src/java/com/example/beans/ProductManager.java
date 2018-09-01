package com.example.beans;

import com.example.dao.InventoryDAO;
import com.example.dao.ProductDAO;
import com.example.model.Product;
import com.example.util.InventoryException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

@RequestScoped
public class ProductManager implements Serializable {

    private static final Logger logger = Logger.getLogger("com.example.beans.ProductManager");

    @Inject
    private ProductDAO productDAO;
    @Inject
    private InventoryDAO inventoryDAO;
    @Inject
    private Validator validator;

    @Transactional(rollbackOn = InventoryException.class)
    public void addNewProduct(Product prod, int quant) throws InventoryException {

        try {
            productDAO.addNewProduct(prod);
            inventoryDAO.updateInventory(prod.getId(), quant);
        } catch (ConstraintViolationException cv) {
            InventoryException ie = new InventoryException(cv.getMessage());
            Set<ConstraintViolation<?>> violations = cv.getConstraintViolations();
            for (ConstraintViolation violation : violations) {
                String error = "Value: " + violation.getInvalidValue() + " Message: " + violation.getMessage();
                ie.addSuppressed(new InventoryException(error));
                logger.log(Level.INFO, error);
            }
            throw ie;
        } catch (Exception e) {
            logger.log(Level.INFO, "foo: " + e.getMessage());
        }
    }

    public List<String> validateProduct(Product p) {
        List<String> messages = new ArrayList<>();
        Set<ConstraintViolation<Product>> violations = validator.validate(p);
        for (ConstraintViolation violation : violations) {
            String error = "Value: " + violation.getInvalidValue() + " Message: " + violation.getMessage();
            messages.add(error);
            logger.log(Level.INFO, error);
        }
        return messages;
    }
}
