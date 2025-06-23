package my.neochat.ChatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.neochat.ChatApp.model.ChatMessage;

@Repository
public interface RepositoryMessage extends JpaRepository<ChatMessage, Long>{
}
