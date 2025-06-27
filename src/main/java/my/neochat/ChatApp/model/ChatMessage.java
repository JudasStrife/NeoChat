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
    private Instant date;
    protected ChatMessage(){};
    public ChatMessage(String text, String sender, String receiver)
    {
        this.text=text;
        this.sender=sender;
        this.receiver=receiver;
        date=Instant.now();
    }
    @Override
    public String toString()
    {
        return String.format(
            "Message[id=%d, text=%s, from=%s, to=%s, date"+date.toString()+"]",
            id, text, sender, receiver);
    }
}
