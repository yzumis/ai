package com.yzumis.talk.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yzumis.talk.model.message.Message;
import com.yzumis.talk.model.message.MessageByUserSenderIdAndUserReceiverId;
import com.yzumis.talk.repository.MessageRepository;

public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping(method= RequestMethod.POST, value="/messages/save")
    public Message save(@RequestBody final Message message) {
        message.setDate(new Date());
        messageRepository.save(message);
        return message;
    }

    @RequestMapping(method= RequestMethod.POST, value="/messages/findByUserSenderIdAndUserReceiverId")
    public List<Message> findByUserSenderIdAndUserReceiverId(@RequestBody final MessageByUserSenderIdAndUserReceiverId messageByUserSenderIdAndUserReceiverId) {
        return messageRepository.findByUserSenderIdAndUserReceiverId(messageByUserSenderIdAndUserReceiverId.getUserSenderId(), messageByUserSenderIdAndUserReceiverId.getUserReceiverId());
    }

}
