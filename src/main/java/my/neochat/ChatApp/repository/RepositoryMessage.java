package my.neochat.ChatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import my.neochat.ChatApp.model.ChatMessage;

@Repository
public interface RepositoryMessage extends JpaRepository<ChatMessage, Long>
{
    @Transactional
    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(
    value = "INSERT INTO messages VALUES (:username, :password) ON CONFLICT (username) DO NOTHING",
    nativeQuery = true)
    public void registerUser(@Param("sender") String sender, @Param("receiver") String receiver, @Param("message") ChatMessage message);

}
