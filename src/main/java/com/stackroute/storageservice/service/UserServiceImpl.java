package com.stackroute.storageservice.service;

import com.stackroute.storageservice.domain.User;
import com.stackroute.storageservice.exception.UserAlreadyExists;
import com.stackroute.storageservice.exception.UserNotFoundException;
import com.stackroute.storageservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExists {

        if(userRepository.existsById(user.getUserId())) {
            throw new UserAlreadyExists("user Already Exists");
        }
        User savedUser=userRepository.save(user);
        return savedUser;
    }

    @Override
    public boolean deleteUser(String userId) throws  UserNotFoundException{
        boolean status = false;
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            status=true;
            return status;

        }
        else {
            throw new UserNotFoundException("userId not exists");
        }

    }

    @Override
    public User updateUser(User user) throws UserNotFoundException{
        if(userRepository.existsById(user.getUserId()))
        {
            User savedUser=userRepository.save(user);
            return  savedUser;
        }
        else{
            throw new UserNotFoundException("user not found");
        }

    }


}
