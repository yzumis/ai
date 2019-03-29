package com.yzumis.talk.controllers;

import com.yzumis.talk.expception.IncorrectUserOrPasswordException;
import com.yzumis.talk.expception.UserAlreadyRegisteredException;
import com.yzumis.talk.model.user.UserRegister;
import com.yzumis.talk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yzumis.talk.model.user.User;
import com.yzumis.talk.model.user.UserLogin;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method= RequestMethod.POST, value="/users/register")
    public void register(@RequestBody final UserRegister userRegister) throws UserAlreadyRegisteredException {
        userService.register(userRegister);
    }

    @RequestMapping(method= RequestMethod.POST, value="/users/login")
    public User login(@RequestBody final UserLogin userLogin) throws IncorrectUserOrPasswordException {
        return userService.login(userLogin);
    }

}
