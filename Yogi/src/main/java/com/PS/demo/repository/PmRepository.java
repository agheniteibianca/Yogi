package com.PS.demo.repository;

import com.PS.demo.model.Offer;
import com.PS.demo.model.Pm;
import com.PS.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PmRepository extends CrudRepository<Pm,Long> {
}
