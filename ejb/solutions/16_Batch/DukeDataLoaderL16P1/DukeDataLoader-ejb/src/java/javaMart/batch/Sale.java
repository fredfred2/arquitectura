package javaMart.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Sale {

    private int status;
    private Date date;
    private double total;
    private final List<Item> items = new ArrayList<>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Sale:" + total + "[" + items.size() + "]" + Arrays.toString(items.toArray());
    }

}
