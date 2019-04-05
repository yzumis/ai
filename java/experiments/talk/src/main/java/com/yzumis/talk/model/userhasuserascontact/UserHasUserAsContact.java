package com.yzumis.talk.model.userhasuserascontact;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity(name = "user_has_user_as_contact")
@IdClass(UserHasUserAsContactId.class)
public class UserHasUserAsContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer user_iduser;

    @Id
    private Integer user_iduser_contact;

    public Integer getUser_iduser() {
        return user_iduser;
    }

    public void setUser_iduser(Integer user_iduser) {
        this.user_iduser = user_iduser;
    }

    public Integer getUser_iduser_contact() {
        return user_iduser_contact;
    }

    public void setUser_iduser_contact(Integer user_iduser_contact) {
        this.user_iduser_contact = user_iduser_contact;
    }

}
