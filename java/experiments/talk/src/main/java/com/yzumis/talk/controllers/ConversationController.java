package com.yzumis.talk.controllers;

import com.yzumis.talk.model.conversation.ConversationCreate;
import com.yzumis.talk.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(method= RequestMethod.POST, value="/conversations/create")
    public void save(@RequestBody final ConversationCreate conversationCreate) {
        conversationService.save(conversationCreate);
    }

}
