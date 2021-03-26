package com.PS.demo.service;

import com.PS.demo.model.Comment;
import com.PS.demo.model.Pm;
import org.springframework.stereotype.Component;

@Component
public interface PmService {
    //create
    public void addPm(Pm pm);

    //delete
    public void deleteById(Long id);
}
