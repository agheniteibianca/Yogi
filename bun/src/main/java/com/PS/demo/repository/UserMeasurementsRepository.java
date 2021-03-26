package com.PS.demo.repository;

import com.PS.demo.model.Review;
import com.PS.demo.model.UserMeasurements;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMeasurementsRepository extends CrudRepository<UserMeasurements,Long> {
}
