package com.PS.demo.service;

import com.PS.demo.model.Comment;
import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentService {
    //create
    public void addComment(Comment new_comment);

    //read
    List<Comment> findAll();
    //List<Comment> findByProduct(Product product);

    //delete
    public void deleteById(Long id);
}
