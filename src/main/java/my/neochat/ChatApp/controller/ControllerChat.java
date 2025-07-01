package my.neochat.ChatApp.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.neochat.ChatApp.model.ChatUser;
import my.neochat.ChatApp.service.ServiceChat;

@RestController
public class ControllerChat {
 
    private SimpMessagingTemplate messagingTemplate;
    private final ServiceChat Service;
    public ControllerChat(ServiceChat Service, SimpMessagingTemplate messagingTemplate)
    {
        this.Service=Service;
        this.messagingTemplate=messagingTemplate;
        
    }
    @GetMapping("test")
    public String GetTest(@RequestParam String username)
    {
        return Service.findUser(username).toString();
    }
    @PostMapping("test")
    public String PostTest(@RequestParam String sender, @RequestParam String receiver)
    {
        return Service.findMessage(sender, receiver).toString();
    }
    @GetMapping("login")
    public String GetLogin(Model model)
    {
        return "Please Login";
    }
    
    @PostMapping("login")
    public boolean PostLogin(Model model, @RequestBody ChatUser user)
    {
        return Service.logIn(user);
    }

    @GetMapping("signup")
    public String GetSignUp(Model model)
    {
        return "Please Register";
    }
    
    @PostMapping("signup")
    public void PostSignUp(@RequestBody ChatUser user)
    {
        Service.signUp(user);
    }

    @GetMapping("chat")
    public String GetChat(Model model)
    {
        return "Looking at chats";
    }
    
    @PostMapping("chat")
    public void PostChat(Model model, @RequestParam String username, @RequestBody String message)
    {
        Service.sendMessage(username, message);
    }

    @MessageMapping("direct")
    public void handleChatMessage(@Payload String message, @Header("simpUser") String username)
    {
        messagingTemplate.convertAndSendToUser(username,"/direct", "Message");
    }
 }
