package com.yzumis.talk.controllers;

import com.yzumis.talk.model.message.Message;
import com.yzumis.talk.model.message.MessageCreate;
import com.yzumis.talk.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(method= RequestMethod.POST, value="/messages/save")
    public void save(@RequestBody final MessageCreate messageCreate) {
        messageService.save(messageCreate);
    }

    @RequestMapping(method= RequestMethod.GET, value="/messages/byidconversation")
    public List<Message> messagesByIdConversation(@RequestParam("idConversation") final Integer idConversation) {
        return messageService.messagesByIdConversation(idConversation);
    }

}
