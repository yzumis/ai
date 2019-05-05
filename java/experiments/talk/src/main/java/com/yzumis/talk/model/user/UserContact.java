package com.yzumis.talk.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserContact {

    @Id
    private Integer id;
    private Integer iduser;
    private String username;
    private boolean contact;
    private boolean pendingMessages;

    public UserContact() {
    }

    public UserContact(final User user, final boolean contact, final boolean pendingMessages) {
        this.iduser = user.getIduser();
        this.username= user.getUsername();
        this.contact = contact;
        this.pendingMessages = pendingMessages;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public boolean isPendingMessages() {
        return pendingMessages;
    }

    public void setPendingMessages(boolean pendingMessages) {
        this.pendingMessages = pendingMessages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
