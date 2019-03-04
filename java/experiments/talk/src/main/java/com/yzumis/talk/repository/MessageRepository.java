package com.yzumis.talk.repository;

import org.springframework.data.repository.CrudRepository;

import com.yzumis.talk.model.Message;

public interface MessageRepository extends CrudRepository<Message, String> {

    void findByUserSenderIdAndUserReceiverId(final Message message);

}
