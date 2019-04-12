package com.yzumis.talk.model.message;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Objects;

public class MessageId implements Serializable {

    private static final long serialVersionUID = 2L;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idmessage;
    private Integer conversation_idconversation;
    private Integer user_iduser;

    @Override
    public boolean equals(final Object object) {
        final boolean ret;
        if(object instanceof MessageId) {
            ret = this.equals((MessageId)object);
        } else {
            ret = false;
        }
        return ret;
    }

    private boolean equals(final MessageId messageId) {
        return this.idmessage == messageId.idmessage
                && this.conversation_idconversation == messageId.conversation_idconversation
                && this.user_iduser == messageId.user_iduser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idmessage, this.conversation_idconversation, this.user_iduser);
    }

    public void setIdmessage(Integer idmessage) {
        this.idmessage = idmessage;
    }

    public void setConversation_idconversation(Integer conversation_idconversation) {
        this.conversation_idconversation = conversation_idconversation;
    }

    public void setUser_iduser(Integer user_iduser) {
        this.user_iduser = user_iduser;
    }

    public Integer getIdmessage() {
        return idmessage;
    }

    public Integer getConversation_idconversation() {
        return conversation_idconversation;
    }

    public Integer getUser_iduser() {
        return user_iduser;
    }
}
