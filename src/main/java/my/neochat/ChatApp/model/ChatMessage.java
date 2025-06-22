package my.neochat.ChatApp.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String sender;
    private String receiver;
    private Instant Date;
    protected ChatMessage(){};
    public ChatMessage(String text, String sender, String receiver)
    {
        this.text=text;
        this.sender=sender;
        this.receiver=receiver;
        Date=Instant.now();
    }
    @Override
    public String toString()
    {
        return String.format(
            "Message[id=%d, text=%s, from=%s, to=%s",
            id, text, sender, receiver);
    }
}
@Entity
@Table(name = "users")
public class User
{
    @Id
    private String user;
    private String password;
}
