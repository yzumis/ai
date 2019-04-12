package com.yzumis.talk.services;

import com.yzumis.talk.model.userhasconversation.UserHasConversation;
import com.yzumis.talk.repository.UserHasConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHasConversationService {

    @Autowired
    private UserHasConversationRepository userHasConversationRepository;

    public void save(final UserHasConversation userHasConversation) {
        userHasConversationRepository.save(userHasConversation);
    }

}
