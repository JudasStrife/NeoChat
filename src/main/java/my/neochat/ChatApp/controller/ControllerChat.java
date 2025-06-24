package my.neochat.ChatApp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String GetTest(Model model)
    {
        return Service.findUser("Maga").toString();
    }
    @GetMapping("login")
    public String GetLogin(Model model)
    {
        return "Please Login";
    }
    
    @PostMapping("login")
    public String PostLogin(Model model)
    {
        return "Login done";
    }

    @GetMapping("register")
    public String GetRegister(Model model)
    {
        return "Please Register";
    }
    
    @PostMapping("register")
    public String PostRegister(@RequestParam String username, @RequestParam String password)
    {
        return Service.registerUser(username, password);
    }

    @GetMapping("chat")
    public String GetChat(Model model)
    {
        return "Looking at chats";
    }
    
    @PostMapping("chat")
    public String PostChat(Model model)
    {
        return "Message sent";
    }
 }
