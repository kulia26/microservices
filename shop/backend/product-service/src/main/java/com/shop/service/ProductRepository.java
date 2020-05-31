package com.shop.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("SELECT u FROM Product u WHERE u.name LIKE %?1%")
    Iterable<Product> findByName(String name);
    @Query("SELECT u FROM Product u WHERE u.id = ?1")
    Product find(Integer id);
    @Query("SELECT u FROM Product u WHERE u.type = ?1")
    Iterable<Product> findByType(String type);

    @Transactional
    @Modifying
    @Query("UPDATE Product u SET u.name = :name, u.price = :price, u.type = :type, u.description = :description, u.rating = :rating WHERE id = :id")
    Integer customUpdate(@Param("id") Integer id,
                         @Param("name") String name,
                         @Param("price") Float price,
                         @Param("type") String type,
                         @Param("description") String description,
                         @Param("rating") Integer rating);

}