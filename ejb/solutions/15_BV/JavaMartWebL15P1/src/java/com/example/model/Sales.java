package com.example.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Sales implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sales_id;
    @Temporal(TemporalType.DATE)
    private Date date_sold;
    private double total_sale;

    public Sales() {
    }
    
    public Sales(int sales_id, Date sales_date, double total_sale) {
        this.sales_id = sales_id;
        this.date_sold = sales_date;
        this.total_sale = total_sale;
    }

    public int getSales_id() {
        return sales_id;
    }

    public Date getSales_date() {
        return date_sold;
    }

    public void setSales_date(Date sales_date) {
        this.date_sold = sales_date;
    }

    public double getTotal_sale() {
        return total_sale;
    }

    public void setTotal_sale(double total_sale) {
        this.total_sale = total_sale;
    }
}
