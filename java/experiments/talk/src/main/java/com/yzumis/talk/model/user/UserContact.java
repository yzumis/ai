package com.yzumis.talk.model.user;

public class UserContact {

    private Integer iduser;
    private String username;
    private boolean contact;

    public UserContact(final User user, final boolean contact) {
        this.iduser = user.getIduser();
        this.username= user.getUsername();
        this.contact = contact;
    }

    public Integer getIduser() {
        return iduser;
    }

    public String getUsername() {
        return username;
    }

    public boolean isContact() {
        return contact;
    }
}
