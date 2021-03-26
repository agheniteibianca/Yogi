package com.PS.demo.repository;

import com.PS.demo.model.Pm;
import com.PS.demo.model.ProductMeasurements;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMeasurementsRepository extends CrudRepository<ProductMeasurements,Long> {
}
