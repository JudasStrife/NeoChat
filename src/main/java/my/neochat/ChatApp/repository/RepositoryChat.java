package my.neochat.ChatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.neochat.ChatApp.model.ChatUser;

@Repository
public interface RepositoryChat extends JpaRepository<ChatUser, String>{
   public ChatUser findByusername(String username);
}
