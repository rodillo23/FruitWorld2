package com.example.joserodolfofigueroachavez.fruitworld2.adapter;

import android.app.Activity;
import android.widget.Toast;

public class Fruit {

    private String name;
    private String description;
    private int imgBackround;
    private int imgIcon;
    private int quantity;
    private Activity activity;

    public static final int LIMIT_QUANTITY = 10;
    public static final int RESET_QUANTITY = 0;

    public Fruit(String name, String description, int imgBackround, int imgIcon, int quantity, Activity activity) {
        this.name = name;
        this.description = description;
        this.imgBackround = imgBackround;
        this.imgIcon = imgIcon;
        this.quantity = quantity;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgBackround() {
        return imgBackround;
    }

    public void setImgBackround(int imgBackround) {
        this.imgBackround = imgBackround;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quatity){
        if (this.quantity < LIMIT_QUANTITY){
            this.quantity += quatity;
        }else{
            Toast.makeText(activity, "Estas en el límite", Toast.LENGTH_SHORT).show();
        }
    }

    public void resetQuanttity(){
        this.quantity = RESET_QUANTITY;
    }
}
