package com.PS.demo.service.impl;

import com.PS.demo.model.User;
import com.PS.demo.repository.UserRepository;
import com.PS.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    //injectare prin constructor
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //create
    public void addUser(User new_user){
        userRepository.save(new_user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //read
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
    @Override
    public User findFirstByUsername(String username) {
        User user = userRepository.findFirstByUsername(username);
        return user;
    }
    @Override
    public User findFirstByUsernameAndPassword(String username, String password) {
        User user = userRepository.findFirstByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User findFirstById(Long Id) {
        return userRepository.findFirstById(Id);
    }
    @Override
    public User findById(Long Id) {
        return userRepository.findById(Id).orElseThrow();
    }


    //update

    @Override
    @Transactional
    public User changeUsername(User dto, String new_username) { //data transfer object
        User usr = userRepository.findById(dto.getId()).orElseThrow();
        usr.setUsername(new_username);
        userRepository.save(usr);
        return usr;
    }

    @Override
    public User increaseSold(User dto) {
        User usr = userRepository.findById(dto.getId()).orElseThrow();
        usr.setItemssold(usr.getItemssold()+1);
        userRepository.save(usr);
        return usr;
    }

    //delete
    @Override
    @Transactional
    public void deleteUser(User dto){
        User usr = userRepository.findById(dto.getId()).orElseThrow();
        userRepository.delete(usr);
    }

    @Override
    public void deleteById(Long id) {
        User usr = userRepository.findById(id).orElseThrow();
        userRepository.delete(usr);
    }



}
