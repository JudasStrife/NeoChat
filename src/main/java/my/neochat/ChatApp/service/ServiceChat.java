package my.neochat.ChatApp.service;

import org.springframework.stereotype.Service;

import my.neochat.ChatApp.model.ChatUser;
import my.neochat.ChatApp.repository.RepositoryMessage;
import my.neochat.ChatApp.repository.RepositoryUser;
@Service
public class ServiceChat {
    private SignUpProcessor signUpProcessor;
    private LogInInitials logInInitials;  
    private final RepositoryUser repositoryUser;
    private final RepositoryMessage repositoryMessage;

    public ServiceChat(RepositoryUser repositoryUser, RepositoryMessage repositoryMessage,SignUpProcessor signUpProcessor,LogInInitials logInInitials)
    {
        this.repositoryUser=repositoryUser;
        this.repositoryMessage=repositoryMessage;
        this.signUpProcessor=signUpProcessor;
        this.logInInitials=logInInitials;
    }

    public ChatUser findUser(String username)
    {
        return repositoryUser.findByusername(username);
    }
    public void signUp(ChatUser user)
    {
        signUpProcessor.set(user);
        signUpProcessor.signUp();
    }

    public boolean logIn(ChatUser user)
    {
        if (repositoryUser.logInCheck(user.getUsername(), user.getPassword()) )
         {
            logInInitials.setUsername(user.getUsername());
            return true;
         }
         else return false;
    }
    
}
