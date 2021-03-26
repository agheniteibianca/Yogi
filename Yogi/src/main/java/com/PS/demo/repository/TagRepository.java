package com.PS.demo.repository;

import com.PS.demo.model.Review;
import com.PS.demo.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
}
