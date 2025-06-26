package my.neochat.ChatApp.service;

import org.springframework.stereotype.Service;

import my.neochat.ChatApp.model.ChatUser;
import my.neochat.ChatApp.repository.RepositoryMessage;
import my.neochat.ChatApp.repository.RepositoryUser;
@Service
public class ServiceChat {
    private SignUpProcessor signUpProcessor;  
    private final RepositoryUser repositoryUser;
    private final RepositoryMessage repositoryMessage;

    public ServiceChat(RepositoryUser repositoryUser, RepositoryMessage repositoryMessage,SignUpProcessor signUpProcessor)
    {
        this.repositoryUser=repositoryUser;
        this.repositoryMessage=repositoryMessage;
        this.signUpProcessor=signUpProcessor;
    }

    public ChatUser findUser(String username)
    {
        return repositoryUser.findByusername(username);
    }
    public void signUp(ChatUser user)
    {
        signUpProcessor.signUp(user);
    }

    public boolean logIn(ChatUser user)
    {
        return repositoryUser.logInCheck(user.getUsername(), user.getPassword());
    }
}
