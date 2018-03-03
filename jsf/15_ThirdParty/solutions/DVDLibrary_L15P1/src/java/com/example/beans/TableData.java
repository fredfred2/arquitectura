package com.test.beans;

/**
 *
 * @author D.Geary, C.Horstmann
 */
import com.example.beans.DVDLibraryBean;
import com.example.beans.SortFilterModel;
import com.example.entities.Item;
import java.io.Serializable;
import java.util.Comparator;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class TableData implements Serializable {
   private SortFilterModel<Item> filterModel;

   @Inject @Named ("dvd") private DVDLibraryBean dvd;

   public TableData() {
       
   }

   public DataModel<Item> getLibrary() {
       if (filterModel == null || dvd.isDVDCollectionUpdated()) {
           filterModel = new SortFilterModel<Item>(new ArrayDataModel(dvd.getDvdCollection().toArray()));
           dvd.setDVDCollectionUpdated(false);
       }
       return filterModel;
   }

   public void setDvd(DVDLibraryBean dvd) {
       this.dvd = dvd;
   }

   public DVDLibraryBean getDvd () {
       return this.dvd;
   }

   public String sortByTitle() {
      filterModel.sortBy(new Comparator<Item>() {
         public int compare(Item n1, Item n2) {
            return n1.getTitle().compareTo(n2.getTitle());
         }
      });
      return null;
   }

   public String sortByYear() {
      filterModel.sortBy(new Comparator<Item>() {
         public int compare(Item n1, Item n2) {
            return n1.getReleaseyear().compareTo(n2.getReleaseyear());
         }
      });
      return null;
   }

      public String sortByGenre() {
      filterModel.sortBy(new Comparator<Item>() {
         public int compare(Item n1, Item n2) {
            return n1.getGenre().compareTo(n2.getGenre());
         }
      });
      return null;
   }

}
