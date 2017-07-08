
package com.example.model;

import com.example.util.ItemCondition;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue(value = "AUTOITEM")
public class AutoItem extends Item {
    @Column(name = "AUTOVIN")
    private String autoVehicleIdNumber;
    private int mileage;

    public AutoItem() {
    }

    public AutoItem(int itemId, Image image, String title, String description, ItemCondition condition, String autoVehicleIdNumber, int mileage) {
        super(itemId, image, title, description, condition);
        this.autoVehicleIdNumber = autoVehicleIdNumber;
        this.mileage = mileage;
    }

    public String getAutoVehicleIdNumber() {
        return autoVehicleIdNumber;
    }

    public void setAutoVehicleIdNumber(String autoVehicleIdNumber) {
        this.autoVehicleIdNumber = autoVehicleIdNumber;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
