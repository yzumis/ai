package com.yzumis.talk.controllers;

import java.util.List;

import com.yzumis.talk.model.user.UserRegister;
import com.yzumis.talk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yzumis.talk.model.user.User;
import com.yzumis.talk.model.user.UserLogin;
import com.yzumis.talk.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method= RequestMethod.POST, value="/users/register")
    public User register(@RequestBody final UserRegister userRegister) {
        return userService.register(userRegister);
    }

    @RequestMapping(method= RequestMethod.POST, value="/users/login")
    public User login(@RequestBody final UserLogin userLogin) {
        return userService.login(userLogin);
    }

}
