/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import java.io.Serializable;  
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;  

/**
 *
 * @author admin
 */
@Named("chartBean")
@SessionScoped
public class GenreChartBean implements Serializable {
 
  
  
    private PieChartModel pieModel;  
  
    public GenreChartBean() {  
        createPieModel();  
    }  
  
    public PieChartModel getPieModel() {  
        return pieModel;  
    }  
  
    private void createPieModel() {  
        pieModel = new PieChartModel();  
  // write code to invoke a mthod that will retrieve the data from the Item table using the query (select genre, count( genre) from ORACLE.ITEM group by genre)
        pieModel.set("Action", 2); 
        pieModel.set("Comedy",1);  
        pieModel.set("Mystery", 3);  
        pieModel.set("Drama", 6);  
        pieModel.set("Sci Fi",2);  
        
        
    }  
}

