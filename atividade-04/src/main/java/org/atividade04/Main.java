package org.atividade04;

import java.sql.SQLException;
import java.util.List;

import org.atividade04.dao.UserDao;
import org.atividade04.model.User;

public class Main {
    public static void main(String[] args) {
        try {
            UserDao userDao = new UserDao();
            List<User> users = userDao.findAll();
            System.out.println(users);
        } catch (SQLException e) {
            System.out.println("erro");
        }
    }
}
