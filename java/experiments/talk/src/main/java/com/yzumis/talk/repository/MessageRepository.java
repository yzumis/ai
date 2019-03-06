package com.yzumis.talk.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.yzumis.talk.model.message.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{userSenderId: ?0, userReceiverId: ?1 }")
    List<Message> findByUserSenderIdAndUserReceiverId(final String userSenderId, final String userReceiverId);

}
