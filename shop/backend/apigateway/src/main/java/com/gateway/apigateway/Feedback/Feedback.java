package com.gateway.apigateway.Feedback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Feedback {
    private Integer id;
    private Integer bookingId;
    private Date date;
    private Integer rating;
    private String text;

    private Integer productId;

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

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}