package com.yzumis.talk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class Message {

    @Id
    private final String id;
    private final String userSenderId;
    private final String userReceiverId;
    private final String text;

    public Message(final String id, final String userSenderId, final String userReceiverId, final String text) {
        this.id = id;
        this.userSenderId = userSenderId;
        this.userReceiverId = userReceiverId;
        this.text = text;
    }
}
