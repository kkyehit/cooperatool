package com.soongsil.sixfourfour.controller;

import com.soongsil.sixfourfour.model.User;
import com.soongsil.sixfourfour.model.request.UserCreateRequest;
import com.soongsil.sixfourfour.repository.UserRepository;
import com.soongsil.sixfourfour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    public void readUser(@PathVariable long id){
        userService.readUser(id);
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public User createUser(@RequestBody UserCreateRequest userCreateRequest){

        User user = userService.createUser(userCreateRequest.getName(), userCreateRequest.getPhone());
        return user;

    }
    @RequestMapping("/hello")
    public User userlist(){
        User user = userService.readUser(5213);
        return user;
    }


}
