package com.cho.springbootmall.service.impl;


import com.cho.springbootmall.dao.UserDao;
import com.cho.springbootmall.dto.UserLoginRequest;
import com.cho.springbootmall.dto.UserRegisterRequest;
import com.cho.springbootmall.model.User;
import com.cho.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserByID(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        //check register email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null){
            log.warn("Email {} has been registered", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //register email
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if (user == null){
            log.warn("email {} not register", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().equals(userLoginRequest.getPassword())) {
            return user;
        } else {
            log.warn("email {} password is not correct", userLoginRequest.getPassword());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
