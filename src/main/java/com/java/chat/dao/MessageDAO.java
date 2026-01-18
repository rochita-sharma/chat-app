package com.java.chat.dao;

import com.java.chat.config.DatabaseConfig;
import com.java.chat.models.Message;
import com.java.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    private static final String sql=
            "INSERT INTO messages (sender, receiver, content, delivered) values (?, ?, ?, ?)";

    public void save(Message message, Boolean delivered){
        try(Connection con = DatabaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, message.getSender());
            ps.setString(2, message.getReceiver());
            ps.setString(3, message.getContent());
            ps.setBoolean(4, delivered);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Message> getUndeliveredMessages(String receiver) {
        List<Message> messages = new ArrayList();
        String sql = "SELECT id, sender, receiver, content FROM messages" +
                "Where receiver=? AND delivered = false ORDER BY timestamp";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, receiver);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Message msg = new Message(
                        rs.getString("sender"),
                        rs.getString("receiver"),
                        rs.getString("content")
                );
                msg.setMessageId(rs.getInt("id"));
                messages.add(msg);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public void markDelivered(int messageId){
        String sql = "UPDATE messages SET delivered = true WHERE id = ?";

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, messageId);
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

