package com.yzumis.talk.model.user;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class User {

    @Id
    private Integer id;
    private String username;
    private String password;
    private String token;

    public User(final UserRegister userRegister) {
        this.username = userRegister.getUsername();
        this.password = userRegister.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
