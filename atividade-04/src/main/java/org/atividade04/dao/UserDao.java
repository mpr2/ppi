package org.atividade04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.atividade04.model.User;

public class UserDao {

    public void create(User user) throws SQLException {
        String sql = "INSERT INTO user (username, email, password_hash, name) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getName());
            stmt.executeUpdate();
        }
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM user";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                users.add(user);
            }
            return users;
        }
    }

    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = getUserFromResultSet(rs);
                return user;
            }
        }
        return null;
    }

    public User findByName(String name) throws SQLException {
        String sql = "SELECT * FROM user WHERE name = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = getUserFromResultSet(rs);
                return user;
            }
        }
        return null;
    }

    public boolean update(User user) throws SQLException {
        String sql = "UPDATE user SET username=?, email=?, password_hash=?, name=? WHERE user_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getName());
            stmt.setInt(5, user.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected != 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected != 0;
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setName(rs.getString("name"));
        user.setCreatedTime(rs.getTimestamp("created_time"));
        
        return user;
    }
}
