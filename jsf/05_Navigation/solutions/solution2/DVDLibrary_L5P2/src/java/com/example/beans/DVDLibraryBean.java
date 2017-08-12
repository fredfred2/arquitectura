package com.example.beans;


import com.example.exceptions.PreexistingEntityException;
import com.example.entities.Item;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tmcginn
 */
@Named("dvd")
@SessionScoped
public class DVDLibraryBean implements Serializable {

   @Inject
    ItemEJB itembean;
   
      private Item item;
    
    private String title = "";
    private Long releaseyear;
    private String genre = "";

   

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre=genre;
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public Long getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Long releaseyear) {
        this.releaseyear=releaseyear;
    
    }

    public String addDVD() throws PreexistingEntityException, Exception {
       Item item=new Item(itembean.count()+1,title, releaseyear.toString(), genre);
       System.out.println("    count:  "+itembean.count());
       itembean.addItem(item);
       title = "";
        genre = "";
        return "index";

        
    }
}
