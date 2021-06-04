package model;

import java.io.Serializable;

public class Message implements Serializable {
    private final String sender;
    private final String receiver;
    private final String message;

    public Message(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}
