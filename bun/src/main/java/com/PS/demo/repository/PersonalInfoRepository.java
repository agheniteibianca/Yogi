package com.PS.demo.repository;

import com.PS.demo.model.PersonalInfo;
import com.PS.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonalInfoRepository extends CrudRepository<PersonalInfo,Long> {
}
