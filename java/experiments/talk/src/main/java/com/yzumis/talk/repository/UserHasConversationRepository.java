package com.yzumis.talk.repository;

import com.yzumis.talk.model.userhasconversation.UserHasConversation;
import com.yzumis.talk.model.userhasconversation.UserHasConversationId;
import org.springframework.data.repository.CrudRepository;

public interface UserHasConversationRepository extends CrudRepository<UserHasConversation, UserHasConversationId> {
}
