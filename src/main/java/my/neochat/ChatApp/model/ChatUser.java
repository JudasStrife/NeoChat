package my.neochat.ChatApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class ChatUser
{
    @Id
    @Column(name = "username")
    private String username;
    private String password;
    protected ChatUser(){};
    public ChatUser(String username, String password)
    {
        this.username=username;
        this.password=password;
    }
    @Override
    public String toString()
    {
        return String.format(
            "User[username=%s, password=%s]",
            username, password);
    }    
}
