package my.neochat.ChatApp.model;

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
    protected ChatMessage(){};
    public String getText(){return text;}
    public String getSender(){return sender;}
    public String getReceiver(){return receiver;}
    public ChatMessage(String text, String sender, String receiver)
    {
        this.text=text;
        this.sender=sender;
        this.receiver=receiver;
    }
    @Override
    public String toString()
    {
        return String.format(
            "Message[id=%d, text=%s, from=%s, to=%s]",
            id, text, sender, receiver);
    }
}
