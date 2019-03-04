package com.yzumis.talk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yzumis.talk.model.User;
import com.yzumis.talk.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method= RequestMethod.POST, value="/users")
    public User save(@RequestBody final User user) {
        userRepository.save(user);
        return user;
    }

    @RequestMapping(method= RequestMethod.POST, value="/users/login")
    public User login(@RequestBody final User user) {
        userRepository.login(user);
        return user;
    }



}
