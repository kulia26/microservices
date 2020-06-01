package com.gateway.apigateway.Booking;

import com.gateway.apigateway.Product.Product;
import com.gateway.apigateway.User.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Date date;
    private Product product;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(date);
    }

    public void setDate(String date) throws ParseException {
        this.date = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(date);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user) {
        this.userId = user;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() { return this.product; }

    public void setProduct(Product product) { this.product = product; }

    public User getUser() {
        return user;
    }

    public void setUser(User u) {
        this.user = u;
    }
}