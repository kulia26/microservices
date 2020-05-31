package com.gateway.apigateway.Product;

import com.gateway.apigateway.Booking.BookingClient;
import com.gateway.apigateway.CustomException;
import com.gateway.apigateway.Feedback.FeedbackClient;
import com.gateway.apigateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    ProductClient client;

    @Autowired
    FeedbackClient feedbackClient;

    @Autowired
    BookingClient bookingClient;

    @Autowired
    UserClient userClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    Product add(@RequestBody Product product,
                @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return client.add(product);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Product> getAll() {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Product getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    }

    @RequestMapping(path="/find/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Product> getIdByName(@PathVariable String name) {
        return client.getIdByName(name);
    }

    @RequestMapping(path="/filter/{type}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Product> getByFilter(@PathVariable String type) {
        return client.getByFilter(type);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Product update(@PathVariable Integer id,
                   @RequestBody Product product,
                   @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.update(id, product);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        feedbackClient.deleteByProductId(id);
        bookingClient.deleteByProductId(id);
        return client.delete(id);
    }
}
