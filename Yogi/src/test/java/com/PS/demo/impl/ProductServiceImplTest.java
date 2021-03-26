package com.PS.demo.impl;

import com.PS.demo.model.Product;
import com.PS.demo.repository.ProductRepository;
import com.PS.demo.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ProductServiceImplTest {

    private static final String NAME = "ghetute";
    private static final String NON_EXISTING_NAME = "nume care nu exista";

    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;
    private Product newPoduct;

    @BeforeEach
    void setUp() {
        initMocks(this);
        product = new Product();
        product.setName(NAME);
        when(productRepository.findFirstByName(NAME)).thenReturn(product);
        when(productRepository.findFirstByName(NON_EXISTING_NAME)).thenReturn(null);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
    }




    @Test
    void addUser() {

        //given
        productService = new ProductServiceImpl(productRepository);

        //when

        Product prod = new Product(new Long(1),NAME,null,0,false,null,null,null,null,null,null);
        productService.addProduct(prod);
        prod = productService.findFirstByName(NAME);

        //then
        assertNotNull(prod);
        assertEquals(NAME, prod.getName());
    }

    @Test
    void updateUser() {

        //given
        productService = new ProductServiceImpl(productRepository);

        //when
        Product prod = productService.findFirstByName(NAME);
        productService.sellItem(prod);


        //then
        prod = productService.findFirstByName(NAME);

        assertNotNull(prod);
        assertEquals(NAME, prod.getName());
        assertEquals(prod.getIs_sold(), true);
    }


    @Test
    void givenExistingName_whenFindByName_thenFindOne() {
        //given
        productService = new ProductServiceImpl(productRepository);

        //when
        Product product1 = productService.findFirstByName(NAME);

        //then
        assertNotNull(product1);
        assertEquals(NAME, product1.getName());
    }

    @Test
    void givenNonExistingName_whenFindByName_thenReturnNull() {
        productService = new ProductServiceImpl(productRepository);

        Product product1 = productService.findFirstByName(NON_EXISTING_NAME);

        assertNull(product1);
    }
}


