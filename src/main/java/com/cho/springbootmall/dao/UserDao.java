package com.cho.springbootmall.dao;

import com.cho.springbootmall.dto.UserRegisterRequest;
import com.cho.springbootmall.model.User;

public interface UserDao {

    User getUserByID(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
