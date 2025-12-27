package com.java.chat.models;

import lombok.Data;

@Data
public class Message {

    String messageId;
    String sender;
    String receiver;
    String content;
//    MessageType type;
//    long timestamp;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}


