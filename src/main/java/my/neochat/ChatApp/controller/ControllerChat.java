package my.neochat.ChatApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import my.neochat.ChatApp.repository.RepositoryChat;

@Controller
public class ControllerChat {
    private final RepositoryChat Repository;
    public ControllerChat(RepositoryChat Repository)
    {
        this.Repository=Repository;
    }
    @GetMapping("home")
    public String GetChat(Model model)
    {
        return Repository.getData();
    }
 }
