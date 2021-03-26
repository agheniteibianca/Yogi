package com.PS.demo.service.impl;

import com.PS.demo.model.Offer;
import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import com.PS.demo.repository.ProductRepository;
import com.PS.demo.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findFirstById(Long Id) {
        return productRepository.findFirstById(Id);
    }

    @Override
    public Product findFirstByName(String name) {
        return productRepository.findFirstByName(name);
    }

    @Override
    public void addProduct(Product new_product) {
        productRepository.save(new_product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByOwner(User usr) {
        return productRepository.findByOwner(usr);
    }

    @Override
    public Product updateProduct(Long id, String newTitle, String newDescription, float newPrice) {
        Product prodToChange = productRepository.findById(id).orElseThrow();
        prodToChange.setName(newTitle);
        prodToChange.setDescription(newDescription);
        prodToChange.setPrice(newPrice);
        productRepository.save(prodToChange);
        return prodToChange;
    }

    @Override
    public Product sellItem(Product selectedProduct) {
        Product found = productRepository.findById(selectedProduct.getId()).orElseThrow();
        found.setIs_sold(true);
        productRepository.save(found);
        return found;
    }

    @Override
    public void deleteById(Long id) {
        Product prod = productRepository.findById(id).orElseThrow();
        productRepository.delete(prod);
    }

}
