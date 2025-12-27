package com.java.chat.client;

import com.java.chat.server.ChatProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to the Chat Server!!");

            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);

            new Thread(new ClientListener(serverIn)).start();

            Scanner scanner = new Scanner(System.in);
            String msg;
            while(true) {
                msg = scanner.nextLine();
                serverOut.println(msg);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
