package com.yzumis.talk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private final String id;
    private final String username;
    private final String password;

    public User(final String id, final String username, final String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
