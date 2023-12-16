package com.cho.springbootmall.service;

import com.cho.springbootmall.dto.UserRegisterRequest;
import com.cho.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userID);

    Integer register(UserRegisterRequest userRegisterRequest);
}
