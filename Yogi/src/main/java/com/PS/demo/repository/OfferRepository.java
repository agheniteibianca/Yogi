package com.PS.demo.repository;

import com.PS.demo.model.Comment;
import com.PS.demo.model.Offer;
import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends CrudRepository<Offer,Long> {
    Offer findFirstById(Long Id);
    Optional<Offer> findById(Long Id);
    List<Offer> findByProduct(Product pr);

    @Override
    List<Offer> findAll();

    //operatiile din baza de date se fac mult mai rapid
    /*
    @Query("select * from offer")
    List<Offer> findAllOffers();*/

}
