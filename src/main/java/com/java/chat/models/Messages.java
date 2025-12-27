package com.java.chat.models;

import com.java.chat.enums.MessageType;
import lombok.Data;

@Data
public class Messages {

    String messageId;
    String senderId;
    String receiverId;
    String content;
    MessageType type;
    long timestamp;
}
