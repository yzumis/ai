package com.yzumis.talk.repository;

import com.yzumis.talk.model.conversation.Conversation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConversationRepository extends CrudRepository<Conversation, Integer> {

    @Query("SELECT DISTINCT" +
            " c.idconversation " +
            "FROM" +
            " conversation c" +
            " INNER JOIN user_has_conversation uhc1 ON uhc1.conversation_idconversation = c.idconversation " +
            " INNER JOIN user_has_conversation uhc2 ON uhc2.conversation_idconversation = c.idconversation " +
            "WHERE" +
            " uhc1.user_iduser = :idUser " +
            " AND uhc2.user_iduser = :idUserContact ")
    Optional<Integer> selectIdConversationByIdUserAndIdUserContact(@Param("idUser") final Integer idUser, @Param("idUserContact") final Integer idUserContact);

}

