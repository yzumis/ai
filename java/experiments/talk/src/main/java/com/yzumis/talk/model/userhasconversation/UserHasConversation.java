package com.yzumis.talk.model.userhasconversation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "user_has_conversation")
@IdClass(UserHasConversationId.class)
public class UserHasConversation {

    @Id
    private Integer user_iduser;

    @Id
    private Integer conversation_idconversation;

    public UserHasConversation() {
    }

    public UserHasConversation(final Integer userIdUser, final Integer conversationIdConversation) {
        this.user_iduser = userIdUser;
        this.conversation_idconversation = conversationIdConversation;
    }

    public Integer getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(Integer user_iduser) {
        this.user_iduser = user_iduser;
    }

    public Integer getConversation_idconversation() {
        return conversation_idconversation;
    }

    public void setConversation_idconversation(Integer conversation_idconversation) {
        this.conversation_idconversation = conversation_idconversation;
    }

}
