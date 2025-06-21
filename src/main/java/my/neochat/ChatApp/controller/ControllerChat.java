package my.neochat.ChatApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import my.neochat.ChatApp.service.ServiceChat;

@Controller
public class ControllerChat {
    private final ServiceChat Service;
    public ControllerChat(ServiceChat Service)
    {
        this.Service=Service;
    }
    @GetMapping("home")
    public String GetChat(Model model)
    {
        return Service.getData();
    }
 }
