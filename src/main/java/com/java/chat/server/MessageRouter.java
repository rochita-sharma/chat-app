package com.java.chat.server;

public class MessageRouter {
    public static void route(String rawMessage, String sender) {

        String[] parts = rawMessage.split("\\|");
        String type = parts[0];
        String receiver = parts[1];
        String content = parts[2];

        if("ALL".equals(receiver)){
            ClientManager.broadcast(sender + ": " + content);
        } else {
            ClientManager.sendToUser(receiver, sender + ": " + content);
        }
    }
}
