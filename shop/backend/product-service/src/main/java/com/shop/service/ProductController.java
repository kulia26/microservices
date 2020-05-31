package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    Product add(@RequestBody Product product) {
        return  repository.save(product);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Product> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Product getById(@PathVariable int id) throws CustomException {
        return repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
    }

    @RequestMapping(path="/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Product> getIdByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @RequestMapping(path="/filter/{type}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Product> getByFilter(@PathVariable String type) {
        return repository.findByType(type);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody Product update(@PathVariable Integer id,
                                          @RequestBody Product product) throws CustomException {
        repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.customUpdate(id, product.getName(),
                product.getPrice(), product.getType(),
                product.getDescription(), product.getRating());
        product.setId(id);
        return product;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException {
        Product existing = repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.delete(existing);
        return "Deleted";
    }
}
