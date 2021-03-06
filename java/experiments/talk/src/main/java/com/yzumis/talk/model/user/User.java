package com.yzumis.talk.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer iduser;
    private String username;
    private String password;
    private String token;
    private Integer main_conversation_idconversation;

    public User() {
    }

    public User(final UserRegister userRegister) {
        this.username = userRegister.getUsername();
        this.password = userRegister.getPassword();
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
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

    public Integer getMain_conversation_idconversation() {
        return main_conversation_idconversation;
    }

    public void setMain_conversation_id_conversation(Integer main_conversation_idconversation) {
        this.main_conversation_idconversation = main_conversation_idconversation;
    }

}
