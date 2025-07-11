package my.neochat.ChatApp.model;

public class ChatUser {
    private String username;
    private String password;
    public ChatUser(String username, String password)
    {
        this.username=username;
        this.password=password;
    }
    public ChatUser(){}
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setUsername(String username)
    {
        this.username=username;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
}
