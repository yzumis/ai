package com.yzumis.talk.controllers;

import com.yzumis.talk.expception.IncorrectUserOrPasswordException;
import com.yzumis.talk.expception.UserAlreadyRegisteredException;
import com.yzumis.talk.model.user.UserContact;
import com.yzumis.talk.model.user.UserRegister;
import com.yzumis.talk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yzumis.talk.model.user.User;
import com.yzumis.talk.model.user.UserLogin;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method= RequestMethod.GET, value="/users")
    public List<UserContact> users(
            @RequestParam(name="iduser") final Integer iduser,
            @RequestParam(name="usernameFilter", required=false, defaultValue="") final String usernameFilter
    ) {
        return userService.users(iduser, usernameFilter);
    }

    @RequestMapping(method= RequestMethod.POST, value="/users/register")
    public void register(@RequestBody final UserRegister userRegister) throws UserAlreadyRegisteredException {
        userService.register(userRegister);
    }

    @RequestMapping(method= RequestMethod.POST, value="/users/login")
    public User login(@RequestBody final UserLogin userLogin) throws IncorrectUserOrPasswordException {
        return userService.login(userLogin);
    }

    @RequestMapping(method= RequestMethod.GET, value="/users/mainconversationbyiduser")
    public Integer mainConversationIdByIdUser(@RequestParam(name="idUser") final Integer idUser) {
        return userService.mainConversationByIdUser(idUser);
    }

}
