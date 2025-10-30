package com.uady.blackWolfCinema.dao;

import com.uady.blackWolfCinema.model.User;

public interface UserDao {

    User findByUserName(String userName);
    
    boolean existsByEmail(String email);

    void save(User theUser);
    
}
