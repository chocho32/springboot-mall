package com.cho.springbootmall.service.impl;


import com.cho.springbootmall.dao.UserDao;
import com.cho.springbootmall.dto.UserRegisterRequest;
import com.cho.springbootmall.model.User;
import com.cho.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserByID(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
