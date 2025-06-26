package my.neochat.ChatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import my.neochat.ChatApp.model.ChatUser;

@Repository
public interface RepositoryUser extends JpaRepository<ChatUser, String>{

  public ChatUser findByusername(String username);

  @Transactional
  @Modifying(clearAutomatically=true, flushAutomatically=true)
  @Query(
  value = "INSERT INTO users VALUES (:username, :password) ON CONFLICT (username) DO NOTHING",
  nativeQuery = true)
  public void registerUser(@Param("username") String username, @Param("password") String password);

  @Transactional
  @Modifying(clearAutomatically=true, flushAutomatically=true)                  
  @Query(
  value = "DELETE from users WHERE users.username = :username",
  nativeQuery = true)
  public void deleteUser(@Param("username") String username);

  @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 from users WHERE users.username=:username AND users.password=:password) THEN CAST (1 AS BIT) ELSE CAST (0 AS BIT) END", 
  nativeQuery = true)
  public boolean logInCheck(@Param("username") String username, @Param("password") String password);
}
