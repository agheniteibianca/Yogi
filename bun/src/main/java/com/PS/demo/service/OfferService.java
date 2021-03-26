package com.PS.demo.service;

import com.PS.demo.model.Comment;
import com.PS.demo.model.Offer;
import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OfferService {
    //create

    public void addOffer(Offer newOffer);

    //read
    List<Offer> findAll();
    List<Offer> findByProduct(Product pr);

    //set accepted
    Offer acceptOffer(Offer offer);

    //delete
    void deleteOffer(Offer offer);
}
