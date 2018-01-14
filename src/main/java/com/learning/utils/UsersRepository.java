package com.learning.utils;

import com.learning.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("select u from Users u where u.username = :username")
    Users findByName(@Param("username") String username);
}
