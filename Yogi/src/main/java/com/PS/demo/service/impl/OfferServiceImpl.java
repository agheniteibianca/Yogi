package com.PS.demo.service.impl;

import com.PS.demo.model.Comment;
import com.PS.demo.model.Offer;
import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import com.PS.demo.repository.OfferRepository;
import com.PS.demo.service.OfferService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void addOffer(Offer newOffer) {
        offerRepository.save(newOffer);
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> findByProduct(Product pr) {
        return offerRepository.findByProduct(pr);
    }

    @Override
    public Offer acceptOffer(Offer offer) {
        Offer found = offerRepository.findById(offer.getId()).orElseThrow();
        found.setIsAccepted(true);
        offerRepository.save(found);
        return found;
    }

    @Override
    public void deleteOffer(Offer offer) {
        Offer found = offerRepository.findById(offer.getId()).orElseThrow();
        offerRepository.delete(found);
    }
}
