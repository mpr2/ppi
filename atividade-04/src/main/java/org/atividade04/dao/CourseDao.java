package org.atividade04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.atividade04.model.Course;

public class CourseDao {

    public void create(Course course) throws SQLException {
        String sql = "INSERT INTO course (instructor_id, title, description) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, course.getInstructorId());
            stmt.setString(2, course.getTitle());
            stmt.setString(3, course.getDescription());
            stmt.executeUpdate();
        }
    }

    public List<Course> findAll() throws SQLException {
        String sql = "SELECT * FROM course";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                Course course = getCourseFromResultSet(rs);
                courses.add(course);
            }
            return courses;
        }
    }

    public Course findById(int id) throws SQLException {
        String sql = "SELECT * FROM course WHERE course_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Course course = getCourseFromResultSet(rs);
                return course;
            }
        }
        return null;
    }

    public List<Course> findByInstructorId(int id) throws SQLException {
        String sql = "SELECT * FROM course WHERE instructor_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (rs.next()) {
                Course course = getCourseFromResultSet(rs);
                courses.add(course);
            }
            return courses;
        }
    }

    public boolean update(Course course) throws SQLException {
        String sql = "UPDATE course SET instructor_id=?, title=?, description=?, updated_time=? WHERE course_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, course.getInstructorId());
            stmt.setString(2, course.getTitle());
            stmt.setString(3, course.getDescription());
            stmt.setTimestamp(4, Timestamp.from(Instant.now()));
            stmt.setInt(5, course.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected != 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM course WHERE course_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected != 0;
        }
    }

    private Course getCourseFromResultSet(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("course_id"));
        course.setInstructorId(rs.getInt("instructor_id"));
        course.setTitle(rs.getString("title"));
        course.setDescription(rs.getString("description"));
        course.setCreatedTime(rs.getTimestamp("created_time"));
        course.setUpdatedTime(rs.getTimestamp("updated_time"));

        return course;
    }
}
