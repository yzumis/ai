package com.yzumis.talk.controllers;

import com.yzumis.talk.model.userhasuserascontact.UserHasUserAsContact;
import com.yzumis.talk.services.UserHasUserAsContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserHasUserAsContactController {

    @Autowired
    private UserHasUserAsContactService userHasUserAsContactService;

    @RequestMapping(method= RequestMethod.POST, value="/userhasuserascontact/save")
    public void register(@RequestBody final UserHasUserAsContact userHasUserAsContact) {
        userHasUserAsContactService.save(userHasUserAsContact);
    }

    @RequestMapping(method= RequestMethod.POST, value="/userhasuserascontact/delete")
    public void delete(@RequestBody final UserHasUserAsContact userHasUserAsContact) {
        userHasUserAsContactService.delete(userHasUserAsContact);
    }

}
