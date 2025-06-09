package com.example.interior.repositories;

import com.example.interior.models.Message;
import com.example.interior.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT COUNT(m) FROM Message m WHERE m.recipient.id = :recipientId AND m.sender.id = :senderId AND m.isRead = false")
    long countUnreadMessagesFromSender(Long recipientId, Long senderId);

    List<Message> findByRecipientId(Long recipientId);

    @Query("SELECT m FROM Message m WHERE m.recipient.id = :recipientId AND m.sender.id = :senderId AND m.isRead = false")
    List<Message> findUnreadMessagesFromSender(Long recipientId, Long senderId);
    
    @Query("SELECT m FROM Message m WHERE (m.sender = :sender AND m.recipient = :recipient) OR (m.sender = :recipient AND m.recipient = :sender) ORDER BY m.timestamp ASC")
    List<Message> findAllBySenderAndRecipient(@Param("sender") User sender, @Param("recipient") User recipient);
}
