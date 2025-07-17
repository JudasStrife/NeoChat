package my.neochat.ChatApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import my.neochat.ChatApp.model.ChatMessage;
import my.neochat.ChatApp.model.DTOmessage;

@Repository
public interface RepositoryMessage extends JpaRepository<ChatMessage, Long>
{
    @Query(
    value = "SELECT * from messages WHERE messages.sender=:sender AND messages.receiver=:receiver",
    nativeQuery = true)
    public List<ChatMessage> findMessage(@Param("sender") String sender, @Param("receiver") String receiver);
    @Query(
    value = "SELECT (text, sender, receiver) from messages WHERE (messages.sender=:sender AND messages.receiver=:receiver) OR (messages.sender=:receiver AND messages.receiver=:sender)",
    nativeQuery = true)
    public List<DTOmessage> findHistory(@Param("sender") String sender, @Param("receiver") String receiver);
}
