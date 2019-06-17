package com.stackroute.storageservice.contoller;

import com.stackroute.storageservice.domain.User;
import com.stackroute.storageservice.exception.UserAlreadyExists;
import com.stackroute.storageservice.exception.UserNotFoundException;
import com.stackroute.storageservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v2")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user)  throws UserAlreadyExists {
        ResponseEntity responseEntity;
        try{
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        }catch (UserAlreadyExists ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);

        }
        return responseEntity;
    }
    @GetMapping("user")
    public ResponseEntity<?>getAllAlbums(){
        return  new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PatchMapping ("/user")
    public ResponseEntity<?> updateAlbum(@RequestBody User user) throws  UserNotFoundException{
        ResponseEntity responseEntity;
        try{
            userService.updateUser(user);
            responseEntity=new ResponseEntity<String>("Updated Successfully", HttpStatus.CREATED);
        }
        catch(UserNotFoundException exception){
            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteTrack(@PathVariable("userId") String userId) throws UserNotFoundException
    {
        ResponseEntity responseEntity;
        try {
            userService.deleteUser(userId);
            responseEntity = new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
        }
        catch(UserNotFoundException exception){

            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
