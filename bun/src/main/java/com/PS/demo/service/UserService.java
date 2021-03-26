package com.PS.demo.service;

import com.PS.demo.model.Comment;
import com.PS.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    //create
    public void addUser(User new_user);


    //read
    List<User> findAll();
    User findFirstById(Long Id);
    public User findById(Long Id);
    public User findByUsername(String username);
    public User findFirstByUsername(String username);
    public User findFirstByUsernameAndPassword(String username, String password);


    //update
    User changeUsername(User dto, String new_username);
    User increaseSold(User dto);

    //delete
    void deleteUser(User usr);
    void deleteById(Long id);


}
