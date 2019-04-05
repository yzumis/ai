package com.yzumis.talk.model.userhasuserascontact;

import java.io.Serializable;
import java.util.Objects;

public class UserHasUserAsContactId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer user_iduser;
    private Integer user_iduser_contact;

    @Override
    public boolean equals(final Object object) {
        final boolean ret;
        if(object instanceof UserHasUserAsContactId) {
            ret = this.equals((UserHasUserAsContactId)object);
        } else {
            ret = false;
        }
        return ret;
    }

    private boolean equals(final UserHasUserAsContactId userHasUserAsContactId) {
        return this.user_iduser == userHasUserAsContactId.user_iduser && this.user_iduser_contact == userHasUserAsContactId.user_iduser_contact;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.user_iduser, this.user_iduser_contact);
    }

}
