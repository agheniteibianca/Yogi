package com.PS.demo.service.impl;

import com.PS.demo.repository.ProductMeasurementsRepository;
import com.PS.demo.service.ProductMeasurementsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductMeasurementsServiceImpl implements ProductMeasurementsService {
    private final ProductMeasurementsRepository productMeasurementsRepository;

    public ProductMeasurementsServiceImpl(ProductMeasurementsRepository productMeasurementsRepository) {
        this.productMeasurementsRepository = productMeasurementsRepository;
    }
}
