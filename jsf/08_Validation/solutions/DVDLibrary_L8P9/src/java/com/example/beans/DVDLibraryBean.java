package com.example.beans;


import com.example.exceptions.PreexistingEntityException;
import com.example.entities.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
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
private ArrayList genreList = null;
    private String newGenre;

    public String getNewGenre() {
        return newGenre;
    }

    public void setNewGenre(String newGenre) {
        this.newGenre = newGenre;
    }
   

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre=genre;
        
    }
 public void addGenre(String newGenre) {
        if (newGenre.equals("")) {
            return;
        }
        Iterator g = genreList.iterator();
        while (g.hasNext()) {
            SelectItem item = (SelectItem) g.next();
            if (item.getValue().equals(newGenre)) {
                return;
            }
        }
        SelectItem newItem = new SelectItem(newGenre, newGenre);
        genreList.add(newItem);
    }

    public ArrayList getGenreList() {
        if (genreList == null) {
            genreList = new ArrayList<String>();
            List freshGenres = itembean.getGenres();
            Iterator g = freshGenres.iterator();
            while (g.hasNext()) {
                String item = (String) g.next();
                SelectItem n = new SelectItem(item, item);
                genreList.add(n);
            }
        }
        return genreList;
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
public Long getCurrentYear() {
        Calendar rightNow = Calendar.getInstance();
        Long x = new Long(rightNow.get(Calendar.YEAR));
        return x;
    }
 
    public String addDVD() throws PreexistingEntityException, Exception {
        if(!newGenre.equals("")) {
            addGenre(newGenre); 
            setGenre(newGenre);
        }
            Item item = new Item(itembean.count()+1,title, releaseyear.toString(),genre);
        
      System.out.println("    count:  "+itembean.count());
       itembean.addItem(item);
      title = "";
   genre = "";
   releaseyear = getCurrentYear();
   setNewGenre("");
   return "home";

        
    }
}
