/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.main;

import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.Bid;
import com.example.model.Image;
import com.example.model.Item;
import com.example.stub.StubbedAuctionUsers;
import com.example.stub.StubbedAuctions;
import com.example.stub.StubbedBids;
import com.example.stub.StubbedImages;
import com.example.stub.StubbedItems;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author tmcginn
 */
public class PopulateAuctionApplication {

    private EntityManagerFactory emf;
    private ConcurrentHashMap<Integer, Image> images = StubbedImages.getImages();
    private ConcurrentHashMap<Integer, Item> items = StubbedItems.getItems();
    private ConcurrentHashMap<String, AuctionUser> users = StubbedAuctionUsers.getAuctionUsers();
    private ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
    private ConcurrentHashMap<Integer, Bid> bids = StubbedBids.getBids();

    public PopulateAuctionApplication() {
        emf = Persistence.createEntityManagerFactory("AuctionPU");
    }

    public static void main(String[] args) {
        System.out.println("Starting populating...");
        PopulateAuctionApplication p = new PopulateAuctionApplication();
        p.populateImages();
        p.populateItems();
        p.populateAuctionUsers();
        p.populateAuctions();
        p.populateBids();
        System.out.println("Complete!");
    }

    public void populateImages() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Enumeration<Image> list = images.elements();
            while (list.hasMoreElements()) {
                em.persist(list.nextElement());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("populateImages: " + e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void populateItems() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Enumeration<Item> list = items.elements();
            while (list.hasMoreElements()) {
                em.persist(list.nextElement());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("populateItems: " + e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void populateAuctionUsers() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Enumeration<AuctionUser> list = users.elements();
            while (list.hasMoreElements()) {
                em.persist(list.nextElement());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("populateAuctionUsers: " + e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void populateAuctions() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Enumeration<Auction> list = auctions.elements();
            while (list.hasMoreElements()) {
                em.persist(list.nextElement());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("populateAuctions: " + e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void populateBids() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Enumeration<Bid> list = bids.elements();
            while (list.hasMoreElements()) {
                em.persist(list.nextElement());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("populateBids: " + e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
