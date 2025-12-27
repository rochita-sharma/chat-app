package com.java.chat.server;

public class ChatProtocol {
    public static final String LOGIN = "LOGIN";
    public static final String MESSAGE = "MESSAGE";
    public static final String LOGOUT = "LOGOUT";

    // Format: TYPE|sender|receiver|content
    public static String build(String type, String sender,
                               String receiver, String content) {
        return type + "|" + sender + "|" + receiver + "|" + content;
    }
}
