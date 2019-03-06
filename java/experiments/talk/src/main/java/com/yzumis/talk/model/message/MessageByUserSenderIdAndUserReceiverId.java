package com.yzumis.talk.model.message;

public class MessageByUserSenderIdAndUserReceiverId {

    private String userSenderId;
    private String userReceiverId;

    public String getUserSenderId() {
        return userSenderId;
    }

    public void setUserSenderId(final String userSenderId) {
        this.userSenderId = userSenderId;
    }

    public String getUserReceiverId() {
        return userReceiverId;
    }

    public void setUserReceiverId(final String userReceiverId) {
        this.userReceiverId = userReceiverId;
    }
}
