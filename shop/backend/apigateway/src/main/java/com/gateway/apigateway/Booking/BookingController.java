package com.gateway.apigateway.Booking;

import com.gateway.apigateway.CustomException;
import com.gateway.apigateway.Product.Product;
import com.gateway.apigateway.User.User;
import com.gateway.apigateway.Product.ProductClient;
import com.gateway.apigateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    BookingClient client;

    @Autowired
    UserClient userClient;

    @Autowired
    ProductClient productClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking,
                                     @RequestHeader(value = "Authorization") String token) throws CustomException  {
        userClient.isUser(token);
        Booking b = client.add(booking);

        b.setProduct(productClient.getById(b.getProductId()));
        b.setUser(userClient.getById(b.getUserId()));

        return  b;
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll(@RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        Iterable<Booking> bookings = client.getAll();
        for(Booking b: bookings) {
            Product product = null;
            try{
                product = productClient.getById(b.getProductId());
            }catch(Exception e){

            }
            User user = null;
            try{
                user = userClient.getById(b.getUserId());
            }catch(Exception e){
                
            }
            
            b.setProduct(product);
            b.setUser(user);
        }
        return bookings;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException {
        Booking booking = client.getById(id);

        Product product = null;
            try{
                product = productClient.getById(booking.getProductId());
            }catch(Exception e){

            }
            User user= null;
            try{
                user = userClient.getById(booking.getUserId());
            }catch(Exception e){
                
            }

        booking.setProduct(product);
        booking.setUser(user);
        return booking;
    };

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id,
                                                          @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isUser(token);
        Iterable<Booking> bookings = client.getUsersOrders(id);
        for(Booking b: bookings) {
            Product product= null;
            try{
                product = productClient.getById(b.getProductId());
            }catch(Exception e){

            }
            User user= null;
            try{
                user = userClient.getById(b.getUserId());
            }catch(Exception e){
                
            }
            
            b.setProduct(product);
            b.setUser(user);
        }
        return bookings;
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getProductOrders(@PathVariable Integer id,
                                                              @RequestHeader(value = "Authorization") String token) {
        userClient.isUser(token);
        return client.getProductOrders(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.delete(id);
    }
}
