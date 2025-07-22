package my.neochat.ChatApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.neochat.ChatApp.model.ChatMessage;
import my.neochat.ChatApp.model.ChatUser;
import my.neochat.ChatApp.service.ServiceChat;

@Controller
public class ControllerChat {
    Map<String, Object> headers = new HashMap<>();
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
    { if (!userDetailsManager.userExists(newUser.getUsername()))
        {
       System.out.println(newUser.getUsername()+" "+newUser.getPassword());
       var user=User.withUsername(newUser.getUsername())
                    .password(newUser.getPassword())
                    .roles("USER")
                    .build();
        userDetailsManager.createUser(user);
        } else System.out.println("User already exists");
    }
    @GetMapping("chat")
    public String GetChat(Model model, Authentication authentication)
    {
        return "chat";
    }
    @GetMapping("chats")
    public String GetChats(Model model, Authentication authentication)
    {
        return "chats";
    }

    @ResponseBody
    @GetMapping("chat/history/{receiver}")
    public List<ChatMessage> GetHistory(Authentication authentication, @PathVariable("receiver") String receiver)
    {

        List<ChatMessage> history=Service.getHistory(authentication.getName(), receiver);
        return history;
    }
    @ResponseBody
    @GetMapping("chats/history")
    public List<String> GetChatboxes(Authentication authentication)
    {

        List<String> history=Service.getChatboxes(authentication.getName());
        return history;
    }

    @MessageMapping("direct/{user}")
    public void handleChatMessage(@Payload String message, @DestinationVariable("user") String username, Authentication authentication)
    {   
        headers.put("sender",authentication.getName());
        messagingTemplate.convertAndSendToUser(username,"/queue/direct",message,headers);
        headers.clear();
        Service.saveMessage(message,authentication.getName(),username);
        System.out.println(message+"to"+username);
    }
 }
