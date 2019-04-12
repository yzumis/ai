package com.yzumis.talk.model.message;

import java.sql.Timestamp;

public class MessageCreate {

    private Integer conversation_idconversation;
    private Integer user_iduser;
    private String text;
    private Timestamp timestamp;

    public Integer getConversation_idconversation() {
        return conversation_idconversation;
    }

    public Integer getUser_iduser() {
        return user_iduser;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
