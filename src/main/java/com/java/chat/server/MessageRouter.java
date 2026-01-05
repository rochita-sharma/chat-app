package com.java.chat.server;

import com.java.chat.dao.MessageDAO;
import com.java.chat.enums.Status;
import com.java.chat.models.Message;
import com.java.chat.models.User;

public class MessageRouter {
    private static final MessageDAO messageDAO = new MessageDAO();

    public static void route(Message msg, User user) {
        if(user.getStatus() == Status.ONLINE && msg.getReceiver().startsWith("all")){
            messageDAO.save(msg);
            ClientManager.broadcast(msg);
        } else{
            messageDAO.save(msg);
            ClientManager.sendToUser(msg);
        }
    }
}
