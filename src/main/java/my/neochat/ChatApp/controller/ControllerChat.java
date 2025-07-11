package my.neochat.ChatApp.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import my.neochat.ChatApp.service.ServiceChat;

@Controller
public class ControllerChat {
 
    private final SimpMessagingTemplate messagingTemplate;
    private final ServiceChat Service;
    public ControllerChat(ServiceChat Service, SimpMessagingTemplate messagingTemplate)
    {
        this.Service=Service;
        this.messagingTemplate=messagingTemplate;
        
    }
    @GetMapping("test")
    public String GetTest(@RequestParam String username)
    {
        return "1";
    }
    @PostMapping("test")
    public String PostTest(@RequestParam String sender, @RequestParam String receiver)
    {
        return Service.findMessage(sender, receiver).toString();
    }
    @GetMapping("home")
    public String GetHome(Model model)
    {
        System.out.println("Entering home page");        
        return "home";
    }
    @GetMapping("login")
    public String GetLogin(Model model)
    {
        return "login";
    }
    @GetMapping("test-login")
    public String GetTestLogin(Model model)
    {
        System.out.println("Entering custom login page");
        return "login";
    }

    @GetMapping("signup")
    public String GetSignUp(Model model)
    {
        return "Please Register";
    }
    
    @PostMapping("signup")
    public void PostSignUp(@RequestBody String user)
    {
       
    }

    @GetMapping("chat")
    public String GetChat(Model model)
    {
        return "Looking at chats";
    }
    
    @PostMapping("chat")
    public void PostChat(Model model, @RequestParam String username, @RequestBody String message)
    {
        
    }

    @MessageMapping("direct")
    public void handleChatMessage(@Payload String message, @Header("simpUser") String username)
    {
        messagingTemplate.convertAndSendToUser(username,"/direct", "Message");
    }
 }
