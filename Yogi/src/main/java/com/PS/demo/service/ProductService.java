package com.PS.demo.service;

import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {
    //create
    void addProduct(Product new_product);

    //read
    List<Product> findAll();
    Product findFirstById(Long Id);
    Product findFirstByName(String name);
    Optional<Product> findById(Long id);
    public List<Product> findByOwner(User usr);

    //update
    Product updateProduct(Long id, String newTitle, String newDescription, float newPrice);
    Product sellItem(Product selectedProduct);

    //delete
    void deleteById(Long id);
}
