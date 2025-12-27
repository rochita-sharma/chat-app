package com.java.chat.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
    private static final int PORT=5000;
    private static List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main() {
        System.out.println("Chat Server started..");
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Client connected from " + clientSocket.getInetAddress().getHostName());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
