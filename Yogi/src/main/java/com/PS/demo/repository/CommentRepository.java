package com.PS.demo.repository;

import com.PS.demo.model.Comment;
import com.PS.demo.model.Offer;
import com.PS.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
    @Override
    List<Comment> findAll();
    //List<Comment> findByProduct(Product pr);
}
