package com.yzumis.talk.services;

import com.yzumis.talk.model.conversation.Conversation;
import com.yzumis.talk.model.conversation.ConversationCreate;
import com.yzumis.talk.model.userhasconversation.UserHasConversation;
import com.yzumis.talk.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserHasConversationService userHasConversationService;

    @Autowired
    private UserService userService;

    public void save(final ConversationCreate conversationCreate) {
        final Optional<Integer> idConversationOptional = conversationRepository.selectIdConversationByIdUserAndIdUserContact(conversationCreate.getIdUser(), conversationCreate.getIdUserContact());
        final Integer idConversation;
        if(idConversationOptional.isPresent()) {
            idConversation = idConversationOptional.get();
        } else {
            idConversation = saveNewConversation(conversationCreate);
        }
        userService.updateMainConversationIdConversationById(conversationCreate.getIdUser(), idConversation);
    }

    private Integer saveNewConversation(final ConversationCreate conversationCreate) {
        final Conversation conversation = new Conversation();
        final Conversation savedConversation = conversationRepository.save(conversation);
        saveUserHasConversation(conversationCreate.getIdUser(), savedConversation.getIdconversation());
        saveUserHasConversation(conversationCreate.getIdUserContact(), savedConversation.getIdconversation());
        return savedConversation.getIdconversation();
    }

    private void saveUserHasConversation(final Integer idUser, final Integer idConversation) {
        final UserHasConversation userHasConversation = new UserHasConversation(idUser, idConversation);
        userHasConversationService.save(userHasConversation);
    }

}
