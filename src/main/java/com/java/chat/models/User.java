package com.java.chat.models;


import com.java.chat.enums.Status;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Data
public class User {

    private static int nUser = 0;
    private String username;
    private int userId;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Status status;

    public User(Socket socket, String username, Status status) throws IOException {
        this.out = new PrintWriter(socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.userId = nUser;
        this.socket = socket;
        this.username = username;
        this.status = status;
        nUser++;
    }
}
