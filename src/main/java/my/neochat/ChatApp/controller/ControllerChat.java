package my.neochat.ChatApp.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import my.neochat.ChatApp.model.ChatUser;
import my.neochat.ChatApp.service.ServiceChat;

@Controller
public class ControllerChat {
    private final UserDetailsManager userDetailsManager;
    private final SimpMessagingTemplate messagingTemplate;
    private final ServiceChat Service;
    public ControllerChat(ServiceChat Service, SimpMessagingTemplate messagingTemplate, UserDetailsManager userDetailsManager)
    {
        this.Service=Service;
        this.messagingTemplate=messagingTemplate;
        this.userDetailsManager=userDetailsManager;
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
    @GetMapping("signup")
    public String GetSignUp(Model model)
    {
        model.addAttribute("newUser",new ChatUser());
        return "signup";
    }
    
    @PostMapping("signup")
    public void PostSignUp(@ModelAttribute("newUser") ChatUser newUser)
    {
       System.out.println(newUser.getUsername()+" "+newUser.getPassword());
       var user=User.withUsername(newUser.getUsername())
                    .password(newUser.getPassword())
                    .roles("USER")
                    .build();
        userDetailsManager.createUser(user);
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
