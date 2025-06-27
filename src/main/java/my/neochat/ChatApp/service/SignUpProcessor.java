package my.neochat.ChatApp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import my.neochat.ChatApp.model.ChatUser;
import my.neochat.ChatApp.repository.RepositoryUser;

@Lazy
@Component
@RequestScope
public class SignUpProcessor {
    private final RepositoryUser Repository;
    private String username;
    private String password;
    public SignUpProcessor(RepositoryUser Repository)
    {
        this.Repository=Repository;
    }
    public void signUp(ChatUser user)
    {
        this.username=user.getUsername();
        this.password=user.getPassword();
        Repository.registerUser(username, password);
    }
}
