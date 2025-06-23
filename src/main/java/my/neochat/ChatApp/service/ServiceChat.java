package my.neochat.ChatApp.service;

import org.springframework.stereotype.Service;

import my.neochat.ChatApp.model.ChatUser;
import my.neochat.ChatApp.repository.RepositoryUser;
@Service
public class ServiceChat {
    private final RepositoryUser Repository;

    public ServiceChat(RepositoryUser Repository)
    {
        this.Repository=Repository;
    }
    public ChatUser getData()
    {
        return Repository.findByusername("Maga");
    }
}
