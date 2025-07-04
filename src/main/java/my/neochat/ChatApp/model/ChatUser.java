package my.neochat.ChatApp.model;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class ChatUser implements UserDetails
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
    public String getUsername()
    {
        return username;
    }    
    public String getPassword()
    {
        return password;
    }
}
