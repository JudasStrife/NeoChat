package my.neochat.ChatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import my.neochat.ChatApp.model.ChatMessage;

@Repository
public interface RepositoryMessage extends JpaRepository<ChatMessage, Long>
{
    @Query(
    value = "SELECT 1 from messages WHERE u.sender=:sender AND u.receiver=:receiver",
    nativeQuery = true)
    public ChatMessage findMessage(@Param("sender") String sender, @Param("receiver") String receiver);
}
