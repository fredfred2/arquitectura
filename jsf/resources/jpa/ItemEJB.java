package com.example.beans;

import com.example.exceptions.ItemException;
import com.example.entities.Item;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ItemEJB {

    @PersistenceContext(unitName="DVDLibraryPU")
    private EntityManager em;

    public void addItem(Item item) throws ItemException {
        try {
             System.out.println(" item details"+item.getId());
            em.persist(item);
            em.flush();
            System.out.println(" item details"+item.getGenre());
        } catch (EntityExistsException pe) {
            throw new ItemException("Item with id " + item.getId() + " exists.");
        }
    }

    public void deleteItem(int id) throws ItemException {
       Item item= null;
        item= em.find(Item.class, id);
        if (item != null) {
            em.remove(item);
        } else {
            throw new ItemException("Employee with id " + id + " does not exist.");
        }
    }

    public void updateItem(Item item) {
        em.merge(item);
    }

    public Item findItem(int id) {
        return em.find(Item.class, id);
    }

    public List<Item> getAllItems() {
        StringBuilder queryText = new StringBuilder("SELECT item FROM Item as item ORDER BY item.title");
        TypedQuery<Item> query = em.createQuery(queryText.toString(), Item.class);
        List<Item> allItems = query.getResultList();
        return allItems;
    }
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Item> rt = cq.from(Item.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
   public List<String> getGenres() {
      
      // NamedQuery nq=hin
      StringBuilder queryText = new StringBuilder("SELECT DISTINCT item.genre FROM Item as item ORDER BY item.genre");
        TypedQuery<String> query = em.createQuery(queryText.toString(), String.class);
       
        List<String> allgenres = query.getResultList();
//       List <String> allgenres = null;
//        List<Item> items=getAllItems();
//       // for(Item item:items)
//         Iterator<Item> g = items.iterator();
//        while (g.hasNext()) 
//                {
//                   Item item=g.next();
//                    allgenres.add(item.getGenre());
//                }
//        
        
        return allgenres;
    }  
}
