package com.PS.demo.service.impl;

import com.PS.demo.model.Pm;
import com.PS.demo.model.User;
import com.PS.demo.repository.PmRepository;
import com.PS.demo.service.PmService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PmServiceImpl implements PmService {
    private final PmRepository pmRepository;

    public PmServiceImpl(PmRepository pmRepository) {
        this.pmRepository = pmRepository;
    }



    @Override
    public void addPm(Pm pm) {
        pmRepository.save(pm);
    }

    @Override
    public void deleteById(Long id) {
        Pm pm = pmRepository.findById(id).orElseThrow();
        pmRepository.delete(pm);
    }
}
