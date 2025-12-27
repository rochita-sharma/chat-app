package com.java.chat.server;


import com.java.chat.models.Message;

public class ChatProtocol {
    public static Message parse(String raw, String sender){
        if(!raw.startsWith("@")) return null;

        int idx = raw.indexOf(":");
        if(idx == -1) return null;

        String receiver = raw.substring(1,idx).trim();
        String content = raw.substring(idx+1).trim();

        return new Message(sender, receiver, content);
    }
}
