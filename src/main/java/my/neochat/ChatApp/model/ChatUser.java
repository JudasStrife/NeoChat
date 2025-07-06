package my.neochat.ChatApp.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
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
    @Override
    public String getUsername()
    {
        return username;
    }    
    @Override
    public String getPassword()
    {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(()->"READ");
    }
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
