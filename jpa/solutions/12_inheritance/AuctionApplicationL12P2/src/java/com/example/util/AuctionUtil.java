/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author tmcginn
 */
public final class AuctionUtil {
    public static String getTimeRemaining(long endTime) throws AuctionEndedException {
        String result;
        long secValue = 1000;
        long minValue = 60 * secValue;
        long hourValue = 60 * minValue;
        long dayValue = 24 * hourValue;
        
        // Create a date that is now
        GregorianCalendar now = new GregorianCalendar();
        // Calculate the time in milliseconds from now
        long diff = endTime - now.getTime().getTime();
        
        // if the time is negative, throw an exception
        if (diff <= 0) {
            throw new AuctionEndedException("Time expired");
        }
        
        // Use the long time value to calculate the days, hours, mins, seconds
        int days = (int)(diff/dayValue);
        diff -= (days * dayValue);
        int hours = (int)(diff/hourValue);
        diff -= (hours * hourValue);
        int mins = (int)(diff/minValue);
        // Don't bother going all the way to seconds
        //diff -= (mins * minValue);
        //int secs = (int)(diff/secValue);
        
        if (days != 0) {
            result = days + "d:" + hours + "h:" + mins + "m";
        } else if (hours != 0) {
            result = hours + "h:" + mins + "m";
        } else {
            result = mins + "m";
        }
        
        return result;
    }
    
    public static String getTimeRemaining(Date endTime) throws AuctionEndedException {
        return getTimeRemaining(endTime.getTime());
    }
    
    public static Date futureDate(int numDays) {
        Date startDate = new GregorianCalendar().getTime();
        GregorianCalendar future = new GregorianCalendar();
        future.add(Calendar.DAY_OF_MONTH, numDays);
        Date endDate = future.getTime();
        return endDate;
    }
    
    public static float defineIncrement(float price) {
        // For current price less than $50, the increment is $1
        // For price between 50 and 1000, increment is $10
        // For prices over 1000, the increment is $100
        float increment = 1.00f;
        if (price > 50.00f && price < 1000.00f) {
            increment = 10.00f;
        } else if (price > 1000.00f) {
            increment = 100.00f;
        }
        return increment;
    }
}
