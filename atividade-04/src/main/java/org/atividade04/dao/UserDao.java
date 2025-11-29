package org.atividade04.dao;

import java.sql.SQLException;
import java.util.List;

import org.atividade04.model.User;

public interface UserDao {

    void create(User user) throws SQLException;

    List<User> findAll() throws SQLException;

    User findById(int id) throws SQLException;

    User findByName(String name) throws SQLException;

    boolean update(User user) throws SQLException;

    boolean delete(int id) throws SQLException;

}