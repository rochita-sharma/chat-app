package com.java.chat.server;

import com.java.chat.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private User user;

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
        try {
            String username = in.readLine();
            this.user  =  new User(clientSocket, username);
            ClientManager.addClient(username, out);

            String input;
            while ((input = in.readLine()) != null) {
//                MessageRouter.route(input, username);
                ClientManager.broadcast(input);
            }
        } catch (Exception e) {
//            ClientManager.removeClient(username);
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
