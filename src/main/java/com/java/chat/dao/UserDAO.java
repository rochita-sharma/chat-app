package com.java.chat.dao;

import com.java.chat.config.DatabaseConfig;
import com.java.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public void saveUser(User user) {
        String sql = "INSERT INTO users ( username, status ) VALUES ( ?, ? ) " +
                "ON CONFLICT (username) DO UPDATE SET status=EXCLUDED.status, last_seen = now()";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getStatus().toString());
            int rows = ps.executeUpdate();
            System.out.println("User saved/updated, rows=" + rows);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void updateStatus(User user) {
        String sql = "UPDATE users SET status=?, " +
                "last_seen = now() WHERE username=?";
        try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, user.getStatus().toString());
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String fetchUserStatus(String receiver){
        String sql = "SELECT status FROM users WHERE username=?";
        String status = null;
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, receiver);
            ResultSet rs = ps.executeQuery();
            status = rs.getString("status");
            System.out.println("User Status fetched "+ status);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }
}
