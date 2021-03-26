package com.PS.demo.service.impl;

import com.PS.demo.model.Comment;
import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import com.PS.demo.repository.CommentRepository;
import com.PS.demo.service.CommentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment new_comment) {
        commentRepository.save(new_comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    /*@Override
    public List<Comment> findByProduct(Product product) {
        return commentRepository.findByProduct(product);
    }*/

    @Override
    public void deleteById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(comment);
    }
}
