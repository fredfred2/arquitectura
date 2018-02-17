/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java.util;


import com.java.sample.Customer;
import com.java.sample.DataSourceBean;
import java.io.Serializable;
import java.util.Comparator;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.DataModel;
import javax.faces.model.ArrayDataModel;

@ManagedBean
@SessionScoped
public class TableData implements Serializable {
   private SortFilterModel<Customer> filterModel;

   @ManagedProperty(value="#{data}")
   private DataSourceBean data;

   public TableData() {
       
   }

   public DataModel<Customer> getSortedData() {
       if (filterModel == null) {
           filterModel = new SortFilterModel<Customer>(new ArrayDataModel(data.getCustomerData().toArray()));
       }
       return filterModel;
   }

   public void setData(DataSourceBean data) {
       this.data = data;
   }

   public DataSourceBean getData () {
       return this.data;
   }

   public String sortByID() {
      filterModel.sortBy(new Comparator<Customer>() {
         public int compare(Customer n1, Customer n2) {
            return n1.getCustomerID().compareTo(n2.getCustomerID());
         }
      });
      return null;
   }

   public String sortByLastName() {
      filterModel.sortBy(new Comparator<Customer>() {
         public int compare(Customer n1, Customer n2) {
            return n1.getLastName().compareTo(n2.getLastName());
         }
      });
      return null;
   }

      public String sortByFirstName() {
      filterModel.sortBy(new Comparator<Customer>() {
         public int compare(Customer n1, Customer n2) {
            return n1.getFirstName().compareTo(n2.getFirstName());
         }
      });
      return null;
   }

}
