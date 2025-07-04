package my.neochat.ChatApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import my.neochat.ChatApp.model.ChatMessage;
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
    public List<ChatMessage> findMessage(String sender, String receiver)
    {
        return repositoryMessage.findMessage(sender,receiver);
    }
    public void signUp(ChatUser user)
    {
        signUpProcessor.signUp(user);
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
    public void sendMessage(String receiver, String message)
    {
        repositoryMessage.saveAndFlush(new ChatMessage(message, logInInitials.getUsername(), receiver));
    }
}
