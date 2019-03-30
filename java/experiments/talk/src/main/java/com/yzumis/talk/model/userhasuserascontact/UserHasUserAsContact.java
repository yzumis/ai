package com.yzumis.talk.model.userhasuserascontact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "user_has_user_as_contact")
public class UserHasUserAsContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer user_iduser;

    @Id
    private Integer user_iduser_contact;

}
