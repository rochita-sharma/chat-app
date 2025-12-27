package com.java.chat.client;

import java.io.BufferedReader;

public class ClientListener implements Runnable {

    BufferedReader in;

    public ClientListener(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try{
           String msg;
           while((msg = in.readLine()) != null) {
               System.out.println(msg);
           }
        } catch(Exception e){
            System.err.println("Disconnected from server");
        }
    }
}
