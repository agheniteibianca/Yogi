package com.PS.demo.repository;

import com.PS.demo.model.Comment;
import com.PS.demo.model.Product;
import com.PS.demo.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
    User findFirstById(Long Id);
    User findByUsername(String username);
    User findFirstByUsername(String username);
    User findFirstByUsernameAndPassword(String username, String password);

    /*
    @Query("SELECT email FROM user")
    List<String> select();*/

    /*
    @Modifying
    @Query("update User u set u.name = :nume where u.id = :id")
    int updateUserSetStatusForName(@Param("nume") String nume,
                                   @Param("id") Long id);
    //User updateUser(User user);
*/

}

