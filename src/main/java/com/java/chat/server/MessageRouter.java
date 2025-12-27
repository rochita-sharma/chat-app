package com.java.chat.server;

import com.java.chat.models.Message;

public class MessageRouter {
    public static void route(Message msg) {
        if(msg.getReceiver().startsWith("all")){
            ClientManager.broadcast(msg);
        } else{
            ClientManager.sendToUser(msg);
        }
    }
}
