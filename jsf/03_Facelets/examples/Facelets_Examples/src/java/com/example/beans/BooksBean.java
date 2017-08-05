/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
 
import javax.inject.Named;
/**
 *
 * @author admin
 */
@Named("bean")
  @SessionScoped
public class BooksBean implements java.io.Serializable {
    

 
  private ArrayList books = new ArrayList();
   public BooksBean() {
          books.add("Core Java");
          books.add("JSF");
          books.add("JavaEE");
          books.add("EJB");
      }
 
      public ArrayList getBooks() {
          return books;
      }
 
      public void setBooks(ArrayList books) {
          this.books = books;
      }
}
