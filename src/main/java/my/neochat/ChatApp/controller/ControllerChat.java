package my.neochat.ChatApp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import my.neochat.ChatApp.service.ServiceChat;

@RestController
public class ControllerChat {
    private final ServiceChat Service;
    public ControllerChat(ServiceChat Service)
    {
        this.Service=Service;
    }
    @GetMapping("home")
    public String GetChat(Model model)
    {
        return Service.getData().toString();
    }
 }
