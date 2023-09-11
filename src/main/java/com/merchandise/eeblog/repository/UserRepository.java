package com.merchandise.eeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.merchandise.eeblog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT * FROM user WHERE username=? AND password=?
    // JPA Naming Query
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findByUsername(@Param("username") String username);

    // @Query(value="SELECT * FROM user WHERE username=?1 AND password=?2",
    // nativeQuery=true)
    // User login (String username, String password)
}
