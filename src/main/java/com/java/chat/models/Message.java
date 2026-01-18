package com.java.chat.models;

import lombok.Data;

@Data
public class Message {

    int messageId;
    String sender;
    String receiver;
    String content;
    long timestamp;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}


