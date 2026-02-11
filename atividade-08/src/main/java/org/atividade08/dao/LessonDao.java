package org.atividade08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.atividade08.model.Lesson;

public class LessonDao {

	public void create(Lesson lesson) throws SQLException {
		String sql = "INSERT INTO lesson (course_id, title, content, position, notes_file) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, lesson.getCourseId());
			stmt.setString(2, lesson.getTitle());
			stmt.setString(3, lesson.getContent());
			stmt.setInt(4, lesson.getPosition());
			stmt.setString(5, lesson.getNotesFile());
			stmt.executeUpdate();
		}
	}

	public Lesson findById(int id) throws SQLException {
		String sql = "SELECT * FROM lesson WHERE lesson_id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Lesson lesson = getLessonFromResultSet(rs);
				return lesson;
			}
		}
		return null;
	}

	public List<Lesson> findByCourseId(int courseId) throws SQLException {
		String sql = "SELECT * FROM lesson WHERE course_id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, courseId);
			ResultSet rs = stmt.executeQuery();
			List<Lesson> lessons = new ArrayList<>();
			while (rs.next()) {
				Lesson lesson = getLessonFromResultSet(rs);
				lessons.add(lesson);
			}
			return lessons;
		}
	}

	public boolean update(Lesson lesson) throws SQLException {
		String sql = "UPDATE lesson SET course_id=?, title=?, content=?, position=?, notes_file=? WHERE lesson_id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, lesson.getCourseId());
			stmt.setString(2, lesson.getTitle());
			stmt.setString(3, lesson.getContent());
			stmt.setInt(4, lesson.getPosition());
			stmt.setString(5, lesson.getNotesFile());
			stmt.setInt(6, lesson.getId());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected != 0;
		}
	}

	public boolean delete(int id) throws SQLException {
		String sql = "DELETE FROM lesson WHERE lesson_id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected != 0;
		}
	}

	private Lesson getLessonFromResultSet(ResultSet rs) throws SQLException {
		Lesson lesson = new Lesson();
		lesson.setId(rs.getInt("lesson_id"));
		lesson.setCourseId(rs.getInt("course_id"));
		lesson.setTitle(rs.getString("title"));
		lesson.setContent(rs.getString("content"));
		lesson.setPosition(rs.getInt("position"));
		lesson.setNotesFile(rs.getString("notes_file"));

		return lesson;
	}
}
