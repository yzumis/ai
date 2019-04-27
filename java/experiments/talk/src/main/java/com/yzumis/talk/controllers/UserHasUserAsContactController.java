package com.yzumis.talk.controllers;

import com.yzumis.talk.model.userhasuserascontact.UserHasUserAsContact;
import com.yzumis.talk.services.TokenService;
import com.yzumis.talk.services.UserHasUserAsContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserHasUserAsContactController {

    @Autowired
    private UserHasUserAsContactService userHasUserAsContactService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(method= RequestMethod.POST, value="/userhasuserascontact/save")
    public void save(
            @RequestHeader(name="token") final String token,
            @RequestBody final UserHasUserAsContact userHasUserAsContact) {
        tokenService.checkTokenValid(userHasUserAsContact.getUser_iduser(), token);
        userHasUserAsContactService.save(userHasUserAsContact);
    }

    @RequestMapping(method= RequestMethod.POST, value="/userhasuserascontact/delete")
    public void delete(
            @RequestHeader(name="token") final String token,
            @RequestBody final UserHasUserAsContact userHasUserAsContact) {
        tokenService.checkTokenValid(userHasUserAsContact.getUser_iduser(), token);
        userHasUserAsContactService.delete(userHasUserAsContact);
    }

}
