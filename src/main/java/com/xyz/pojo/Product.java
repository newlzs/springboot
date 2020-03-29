package com.xyz.pojo;

import java.io.Serializable;

public class Product implements Serializable,Model {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private long price;
    // getters setters

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
