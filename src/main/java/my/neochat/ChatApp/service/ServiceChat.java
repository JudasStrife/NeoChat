package my.neochat.ChatApp.service;

import org.springframework.stereotype.Service;

import my.neochat.ChatApp.repository.RepositoryChat;
@Service
public class ServiceChat {
    private final RepositoryChat Repository;

    public ServiceChat(RepositoryChat Repository)
    {
        this.Repository=Repository;
    }
    public String getData()
    {
        return Repository.getData();
    }
}
