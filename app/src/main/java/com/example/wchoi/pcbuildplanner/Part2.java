package com.example.wchoi.pcbuildplanner;

import java.io.Serializable;

/**
 * Created by 2015wchoi on 1/22/2015.
 */
public class Part2 implements Serializable{
    private String product;
    private String price;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Part2(String product, String price) {
        this.product = product;
        this.price = price;
    }
}
