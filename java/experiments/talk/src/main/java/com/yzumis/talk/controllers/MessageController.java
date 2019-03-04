package com.yzumis.talk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yzumis.talk.model.Message;
import com.yzumis.talk.repository.MessageRepository;

public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping(method= RequestMethod.POST, value="/messages")
    public Message save(@RequestBody final Message message) {
        messageRepository.save(message);
        return message;
    }

    @RequestMapping(method= RequestMethod.POST, value="/messages/findByUserSenderIdAndUserReceiverId")
    public Message findByUserSenderIdAndUserReceiverId(@RequestBody final Message message) {
        messageRepository.findByUserSenderIdAndUserReceiverId(message);
        return message;
    }

}
