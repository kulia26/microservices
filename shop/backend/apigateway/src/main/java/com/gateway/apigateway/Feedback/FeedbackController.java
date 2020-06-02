package com.gateway.apigateway.Feedback;

import com.gateway.apigateway.Booking.BookingClient;
import com.gateway.apigateway.CustomException;
import com.gateway.apigateway.Product.Product;
import com.gateway.apigateway.Product.ProductClient;
import com.gateway.apigateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/feedback")
public class FeedbackController {
    @Autowired
    FeedbackClient client;

    @Autowired
    ProductClient productClient;

    @Autowired
    BookingClient bookingClient;

    @Autowired
    UserClient userClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Feedback add(@RequestBody Feedback feedback,
                                      @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isUser(token);
        Integer productId = bookingClient.getById(feedback.getBookingId()).getProductId();
        Product product = productClient.getById(productId);
        product.setRating((product.getRating() + feedback.getRating())/2);
        feedback.setProductId(productId);
        productClient.update(productId, product);
        return client.add(feedback);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getAll() throws CustomException {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Feedback getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    }

    @RequestMapping(path = "/booking/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getByOrderId(@PathVariable Integer id) {
        return client.getByOrderId(id);
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.delete(id);
    }

    @RequestMapping(path="/product/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getByProductId(@PathVariable Integer id){
        return client.getByProductId(id);
    }
}
