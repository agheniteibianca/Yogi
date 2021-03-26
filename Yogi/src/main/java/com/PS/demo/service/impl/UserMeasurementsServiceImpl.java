package com.PS.demo.service.impl;

import com.PS.demo.repository.UserMeasurementsRepository;
import com.PS.demo.service.UserMeasurementsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserMeasurementsServiceImpl implements UserMeasurementsService {
    private final UserMeasurementsRepository userMeasurementsRepository;

    public UserMeasurementsServiceImpl(UserMeasurementsRepository userMeasurementsRepository) {
        this.userMeasurementsRepository = userMeasurementsRepository;
    }
}
