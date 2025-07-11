package my.neochat.ChatApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import my.neochat.ChatApp.model.ChatMessage;
import my.neochat.ChatApp.repository.RepositoryMessage;
@Service
public class ServiceChat {
    private final RepositoryMessage repositoryMessage;

    public ServiceChat(RepositoryMessage repositoryMessage)
    {
        this.repositoryMessage=repositoryMessage;
    }

    public List<ChatMessage> findMessage(String sender, String receiver)
    {
        return repositoryMessage.findMessage(sender,receiver);
    }

}
