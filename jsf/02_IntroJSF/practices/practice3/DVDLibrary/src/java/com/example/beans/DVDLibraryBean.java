/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import com.example.entities.Item;
import static com.example.entities.Item_.title;
import com.example.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author fredfred2
 */
@Named("dvd")
@SessionScoped
public class DVDLibraryBean implements Serializable
{
    private String title = "";
    private Long releaseyear;
    private String genre = "";
    @Inject
    ItemEJB itembean;
    private Item item;
    
    public DVDLibraryBean() 
    {
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Long releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String addDVD() throws PreexistingEntityException, Exception {
        item=new Item(itembean.count()+1,title,
        releaseyear.toString(), genre);
        System.out.println("count: "+itembean.count());
        itembean.addItem(item);
        title = "";
        genre = "";
        return "index";
    }
}
