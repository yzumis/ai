package com.yzumis.talk.services;

import com.yzumis.talk.model.message.Message;
import com.yzumis.talk.model.message.MessageCreate;
import com.yzumis.talk.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private static final int DELETE_OLD_MESSAGES_CREATED_BEFORE_DAYS = 7;

    public void save(final MessageCreate messageCreate) {
        final Message message = new Message(messageCreate);
        messageRepository.save(message);
    }

    public List<Message> messagesByIdConversation(final Integer idConversation) {
        return messageRepository.selectMessagesByIdConversation(idConversation);
    }

    @Scheduled(cron = "0 0/5 * * * *")
    private void deleteOldMessages() {
        messageRepository.deleteOldMessages(DELETE_OLD_MESSAGES_CREATED_BEFORE_DAYS);
    }

}
