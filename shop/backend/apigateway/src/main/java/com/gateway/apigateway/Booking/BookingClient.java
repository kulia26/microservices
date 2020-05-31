package com.gateway.apigateway.Booking;

import com.gateway.apigateway.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="booking-service")
public interface BookingClient {
    @RequestMapping(path="/booking", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking);

    @RequestMapping(path="/booking", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll();

    @RequestMapping(path="/booking/{id}", method = RequestMethod.GET)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException;

    @RequestMapping(path = "/booking/users/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id);

    @RequestMapping(path = "/booking/product/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getProductOrders(@PathVariable Integer id);

    @RequestMapping(path="/booking/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException;

    @RequestMapping(path="/booking/product/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteByProductId(@PathVariable Integer id) throws CustomException;
}