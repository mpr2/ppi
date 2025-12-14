package org.atividade05.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.atividade05.model.Category;

public class CategoryDao {
    
    public void create(Category category) throws SQLException {
        String sql = "INSERT INTO category(category_id, name, description) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getId());
            stmt.setString(2, category.getName());
            stmt.setString(3, category.getDescription());
            stmt.executeUpdate();
        }
    }

    public List<Category> findAll() throws SQLException {
        String sql = "SELECT * FROM category";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = getCategoryFromResultSet(rs);
                categories.add(category);
            }
            return categories;
        }
    }

    public Category findById(String id) throws SQLException {
        String sql = "SELECT * FROM category WHERE category_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Category category = getCategoryFromResultSet(rs);
                return category;
            }
        }
        return null;
    }

    public boolean update(Category category) throws SQLException {
        String sql = "UPDATE category SET name=?, description=? WHERE category_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setString(3, category.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected != 0;
        }
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM category WHERE category_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected != 0;
        }
    }

    private Category getCategoryFromResultSet(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getString("category_id"));
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));

        return category;
    }
}
