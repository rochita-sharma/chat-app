package com.java.chat.dao;

import com.java.chat.config.DatabaseConfig;
import com.java.chat.models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MessageDAO {

    private static final String sql=
            "INSERT INTO messages (sender, receiver, content) values (?, ?, ?)";

    public void save(Message message){
        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, message.getSender());
            ps.setString(2, message.getReceiver());
            ps.setString(3, message.getContent());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

