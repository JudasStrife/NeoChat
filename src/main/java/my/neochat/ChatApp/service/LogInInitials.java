package my.neochat.ChatApp.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
class LogInInitials {
    private String username;
    void setUsername(String username)
    {
        this.username=username;
    }
    String getUsername()
    {
        return username;
    }
}
