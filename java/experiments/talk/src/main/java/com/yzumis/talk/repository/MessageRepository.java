package com.yzumis.talk.repository;

import com.yzumis.talk.model.message.Message;
import com.yzumis.talk.model.message.MessageId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepository extends CrudRepository<Message, MessageId> {

    @Query("SELECT" +
            " m " +
            "FROM" +
            " message m " +
            "WHERE" +
            " m.conversation_idconversation = :idConversation " +
            "ORDER BY" +
            " m.timestamp DESC")
    List<Message> selectMessagesByIdConversation(@Param("idConversation") final Integer idConversation);

    @Transactional
    @Query("DELETE FROM" +
            " message m " +
            "WHERE" +
            " m.timestamp < SUBDATE(current_timestamp, 7)")
    @Modifying
    void deleteOldMessages(@Param("deleteOldMessagesCreatedBeforeDays") final Integer deleteOldMessagesCreatedBeforeDays);

}
