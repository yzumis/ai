package com.yzumis.talk.services;

import com.yzumis.talk.model.message.Message;
import com.yzumis.talk.model.message.MessageCreate;
import com.yzumis.talk.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void save(final MessageCreate messageCreate) {
        final Message message = new Message(messageCreate);
        messageRepository.save(message);
    }

    public List<Message> messagesByIdConversation(final Integer idConversation) {
        return messageRepository.selectMessagesByIdConversation(idConversation);
    }

}
