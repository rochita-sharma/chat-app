package com.java.chat.server;

import com.java.chat.dao.MessageDAO;
import com.java.chat.dao.UserDAO;
import com.java.chat.enums.Status;
import com.java.chat.models.Message;
import com.java.chat.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private User user;
    private UserDAO userDAO = new UserDAO();
    private MessageDAO messageDAO = new MessageDAO();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try{
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        out.println("Enter username: ");
        String username = null;
        try {
            username = in.readLine();
            user  =  new User(clientSocket, username, Status.ONLINE);
            ClientManager.addUser(username, out);
            userDAO.saveUser(user);

            List<Message> pending = messageDAO.getUndeliveredMessages(user.getUsername());
            for(Message msg: pending){
                MessageRouter.route(msg, user);
                messageDAO.markDelivered(msg.getMessageId());
            }

            String input;
            while ((input = in.readLine()) != null) {
                Message msg = ChatProtocol.parse(input, username);
                if(msg != null) {
                    MessageRouter.route(msg, user);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if(user!=null) {
                user.setStatus(Status.OFFLINE);
                userDAO.updateStatus(user);
                ClientManager.removeUser(username);
            }
            closeConnection();
        }
    }
    private void closeConnection() {
        try{
            clientSocket.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
