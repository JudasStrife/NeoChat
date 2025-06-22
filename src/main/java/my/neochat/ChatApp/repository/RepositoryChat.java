package my.neochat.ChatApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import my.neochat.ChatApp.model.ChatMessage;

@Repository
public interface RepositoryChat extends CrudRepository<ChatMessage, Long>{
   
}
