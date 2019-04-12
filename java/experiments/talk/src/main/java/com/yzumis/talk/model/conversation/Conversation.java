package com.yzumis.talk.model.conversation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idconversation;

    public Integer getIdconversation() {
        return idconversation;
    }

    public void setIdconversation(Integer idconversation) {
        this.idconversation = idconversation;
    }

}
