package my.neochat.ChatApp.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import my.neochat.ChatApp.model.ChatUser;
import my.neochat.ChatApp.repository.RepositoryMessage;
import my.neochat.ChatApp.repository.RepositoryUser;
@Service
public class ServiceChat {
    private final RepositoryUser repositoryUser;
    private final RepositoryMessage repositoryMessage;

    public ServiceChat(RepositoryUser repositoryUser, RepositoryMessage repositoryMessage)
    {
        this.repositoryUser=repositoryUser;
        this.repositoryMessage=repositoryMessage;
    }

    public ChatUser findUser(String username)
    {
        return repositoryUser.findByusername(username);
    }

    @Transactional
    public String registerUser(String username, String password)
    {
        repositoryUser.registerUser(username, password);
        return "OK";
    }
}
