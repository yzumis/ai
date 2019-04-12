package com.yzumis.talk.model.message;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity(name = "message")
@IdClass(MessageId.class)
public class Message {

    @Id
    private Integer idmessage;
    @Id
    private Integer conversation_idconversation;
    @Id
    private Integer user_iduser;
    private String text;
    private Timestamp timestamp;

    public Message() {
    }

    public Message(final MessageCreate messageCreate) {
        this.conversation_idconversation = messageCreate.getConversation_idconversation();
        this.user_iduser = messageCreate.getUser_iduser();
        this.text = messageCreate.getText();
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public Integer getIdmessage() {
        return idmessage;
    }

    public void setIdmessage(Integer idmessage) {
        this.idmessage = idmessage;
    }

    public Integer getConversation_idconversation() {
        return conversation_idconversation;
    }

    public void setConversation_idconversation(Integer conversation_idconversation) {
        this.conversation_idconversation = conversation_idconversation;
    }

    public Integer getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(Integer user_iduser) {
        this.user_iduser = user_iduser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
