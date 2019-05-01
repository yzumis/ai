package com.yzumis.talk.controllers;

import com.yzumis.talk.model.conversation.ConversationCreate;
import com.yzumis.talk.services.ConversationService;
import com.yzumis.talk.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConversationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(method= RequestMethod.POST, value="/conversations/create")
    public void save(
            @RequestHeader(name="token") final String token,
            @RequestBody final ConversationCreate conversationCreate) {
        tokenService.checkTokenValid(conversationCreate.getIdUser(), token);
        conversationService.save(conversationCreate);
    }

    @RequestMapping(method= RequestMethod.GET, value="/conversations/usernamebyiduserandidconversation")
    public String usernameByIdUserAndIdConversation(
            @RequestHeader(name="token") final String token,
            @RequestParam(name="idUser") final Integer idUser,
            @RequestParam(name="idConversation") final Integer idConversation
    ) {
        tokenService.checkTokenValid(idUser, token);
        return conversationService.usernameByIdUserAndIdConversation(idUser, idConversation);
    }

}
