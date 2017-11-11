package com.example.jsf;

import com.example.ejb.AuctionFacade;
import com.example.ejb.ItemFacade;
import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.Image;
import com.example.model.Item;
import com.example.util.AuctionStatus;
import com.example.util.AuctionUtil;
import com.example.util.JSFMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "itemBean")
@SessionScoped
public class AuctionItemBean implements Serializable {
    
    @Inject
    private AuctionFacade auctionFacade;
    @Inject
    private AuctionManagerBean manager;
    @Inject
    private ItemFacade itemFacade;
    private Item item;
    private Auction auction;
    private Image image;
    private boolean photoUploaded = false;
    private int photoId = -1;
    private final int maxTitle = 50;
    private int numDays = 7;
    private float currPrice = 0.99f;
    // added 7P2
    private String tags;
    
    public AuctionItemBean() {
        item = new Item();
        auction = new Auction();
    }
    
    public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public Item getItem() {
        return item;
    }
    
    public void setItem(Item item) {
        this.item = item;
    }
    
    public Auction getAuction() {
        return auction;
    }
    
    public void setAuction(Auction auction) {
        this.auction = auction;
    }
    
    public boolean isPhotoUploaded() {
        return photoUploaded;
    }
    
    public int getPhotoId() {
        return photoId;
    }
    
    public void setPhotoUploaded(boolean photoUploaded) {
        this.photoUploaded = photoUploaded;
    }
    
    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
    
    public int getNumDays() {
        return numDays;
    }
    
    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }
    
    public float getCurrPrice() {
        return currPrice;
    }
    
    public void setCurrPrice(float currPrice) {
        this.currPrice = currPrice;
    }
    
    public String cancelListing() {
        // If the user cancels, we need to dump the Item and Image object
        // created and stored (if it exists)
        // Auction has not been created yet
        if (photoUploaded) {
            item.setImage(null);
        }
        photoUploaded = false;
        photoId = -1;
        numDays = 7;
        item = new Item();
        auction = new Auction();
        return "home";
    }

    public String createAuction() {
        String result = null;
        if (currPrice < 0.99f) {
            JSFMessage.addErrorMessage("Your Auction starting price cannot be less that 99 cents.");
        } else {
            // Get the data to create an Auction
            AuctionUser seller = manager.getUser();
            // Starting price already set
            float increment = AuctionUtil.defineIncrement(auction.getCurrPrice());
            // modified in L7
            //int status = AuctionStatus.ACTIVE.ordinal;
            Date endDate = AuctionUtil.futureDate(numDays);

            // Set the values into the Auction object
            auction.setSeller(seller);  // AuctionUser
            auction.setItem(item);  // Item
            auction.setCurrPrice(currPrice);
            auction.setIncrement(increment);
            auction.setStatus(AuctionStatus.ACTIVE.ordinal());
            auction.setEndDate(endDate);

            // Call the facade method to create the auction
            // We don't need the returned auction object
            auctionFacade.addAuction(auction);

            // added L7P2
            // Add the tags after the Auction is created
            if (tags != null) {
                itemFacade.addItemTags(item.getItemId(), tags);
            }

            // Build a message to display
            JSFMessage.addInfoMessage("Successfully listed your Auction!");

            //Refresh the Image, Item held locally
            image = new Image();
            item = new Item();
            photoUploaded = false;
            photoId = -1;
            // Clear the locally held auction data
            auction = new Auction();
            result = "home";
        }
        // Return to the home view
        return result;
    }
    
    public int getTitleCount() {
        if (item.getTitle() != null) {
            return (maxTitle - item.getTitle().length());
        } else {
            return maxTitle;
        }
    }
    
    public void addImageContent(InputStream content, String contentType) throws IOException {
        int bytesRead;
        byte[] photo;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192]; // 8K blocks
            while ((bytesRead = content.read(buffer, 0, buffer.length)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            photo = bos.toByteArray();
        }
        image = new Image();
        image.setContent(photo);
        image.setContentType(contentType);
        item.setImage(image);
        photoUploaded = true;
        photoId = 0;
    }
}
