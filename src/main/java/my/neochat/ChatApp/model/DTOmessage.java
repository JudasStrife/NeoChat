package my.neochat.ChatApp.model;

public class DTOmessage {
    private String text;
    private String sender;
    private String receiver;
    public DTOmessage(){}
    public DTOmessage(String text, String sender, String receiver)
    {
        this.text=text;
        this.sender=sender;
        this.receiver=receiver;
    }
    @Override
    public String toString()
    {
        return String.format(
            "Message[text=%s, from=%s, to=%s]",
            text, sender, receiver);
    }
}
