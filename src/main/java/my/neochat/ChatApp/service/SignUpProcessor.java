package my.neochat.ChatApp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import my.neochat.ChatApp.repository.RepositoryUser;

@Lazy
@Component
@RequestScope
public class SignUpProcessor {
    private final RepositoryUser Repository;
    
    public SignUpProcessor(RepositoryUser Repository)
    {
        this.Repository=Repository;
    }
    public void signUp(String username, String password)
    {
        Repository.registerUser(username, password);
    }
}
