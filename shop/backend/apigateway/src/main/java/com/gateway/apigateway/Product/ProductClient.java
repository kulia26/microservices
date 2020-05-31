package com.gateway.apigateway.Product;

import com.gateway.apigateway.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="product-service")
public interface ProductClient {
    @RequestMapping(path="/product", method = RequestMethod.POST)
    public @ResponseBody
    Product add(@RequestBody Product product);

    @RequestMapping(path="/product", method = RequestMethod.GET)
    public @ResponseBody Iterable<Product> getAll();

    @RequestMapping(path="/product/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Product getById(@PathVariable int id) throws CustomException;

    @RequestMapping(path="/product/find/{name}", method = RequestMethod.GET)
    public @ResponseBody
    Product getIdByName(@PathVariable String name);

    @RequestMapping(path="/product/filter/{type}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Product> getByFilter(@PathVariable String type);

    @RequestMapping(path="/product/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Product update(@PathVariable Integer id,
                   @RequestBody Product product) throws CustomException;

    @RequestMapping(path="/product/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException;
}