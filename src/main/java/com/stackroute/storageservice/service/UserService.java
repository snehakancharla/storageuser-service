package com.stackroute.storageservice.service;

import com.stackroute.storageservice.domain.User;
import com.stackroute.storageservice.exception.UserAlreadyExists;
import com.stackroute.storageservice.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User saveUser(User user) throws  UserAlreadyExists;
    public boolean deleteUser(String userId) throws UserNotFoundException;
    public User updateUser(User user) throws UserNotFoundException;
}
