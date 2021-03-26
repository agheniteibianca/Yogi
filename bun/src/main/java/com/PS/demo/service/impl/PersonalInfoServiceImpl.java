package com.PS.demo.service.impl;

import com.PS.demo.repository.PersonalInfoRepository;
import com.PS.demo.service.PersonalInfoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PersonalInfoServiceImpl implements PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;

    public PersonalInfoServiceImpl(PersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }
}
