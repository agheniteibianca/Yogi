package com.PS.demo.repository;

import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findFirstById(Long id);
    Product findFirstByName(String name);

    @Override
    List<Product> findAll();
    List<Product> findByOwner(User usr);




}
