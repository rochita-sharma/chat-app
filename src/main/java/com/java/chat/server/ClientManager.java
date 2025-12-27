package com.java.chat.server;

import com.java.chat.models.User;

import java.io.PrintWriter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {
    private static Set<User> users = ConcurrentHashMap.newKeySet();
    public static void addUser(User user) {
        users.add(user);
    }
    public static void removeUser(User user) {
        users.remove(user);
    }
    public static void sendMessageToUser(User sender, String message, User receiver) {
        
    }
}
