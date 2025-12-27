package com.java.chat.server;

import com.java.chat.models.Message;

import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {
    private static Map<String, PrintWriter> users = new ConcurrentHashMap<>();
    public static void addUser(String username, PrintWriter out) {
        users.put(username, out);
    }
    public static void removeUser(String username) {
        users.remove(username);
    }
    public static void sendToUser(Message msg) {
        PrintWriter out = users.get(msg.getReceiver());
        if (out != null) {
            out.println(msg.getSender()+": "+msg.getContent());
        }
    }
    public static void broadcast(Message msg) {
        for(String username : users.keySet()) {
            if(!username.equals(msg.getSender())) {
                PrintWriter out = users.get(username);
                out.println(msg.getSender()+": "+msg.getContent());
            }
        }
    }
}
