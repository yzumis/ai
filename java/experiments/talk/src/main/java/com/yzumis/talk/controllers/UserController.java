package com.yzumis.talk.controllers;

import com.yzumis.talk.exception.IncorrectUserOrPasswordException;
import com.yzumis.talk.exception.UserAlreadyRegisteredException;
import com.yzumis.talk.model.user.UserContact;
import com.yzumis.talk.model.user.UserRegister;
import com.yzumis.talk.services.TokenService;
import com.yzumis.talk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yzumis.talk.model.user.User;
import com.yzumis.talk.model.user.UserLogin;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(method= RequestMethod.GET, value="/users")
    public List<UserContact> users(
            @RequestHeader(name="token") final String token,
            @RequestParam(name="iduser") final Integer iduser,
            @RequestParam(name="usernameFilter", required=false, defaultValue="") final String usernameFilter
    ) {
        tokenService.checkTokenValid(iduser, token);
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
    public Integer mainConversationIdByIdUser(
            @RequestHeader(name="token") final String token,
            @RequestParam(name="idUser") final Integer idUser
    ) {
        tokenService.checkTokenValid(idUser, token);
        return userService.mainConversationByIdUser(idUser);
    }

}
