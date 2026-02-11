package org.atividade08.bean;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.atividade08.dao.UserDao;
import org.atividade08.model.User;

public class UserBean {
    private User user;
    private int id;
    private String username;
    private String email;
    private String name;
    private Timestamp createdTime;
    private String profilePic;
    private UserDao userDao;
    private String errorMessage;

    public boolean initialize(int id) {
        userDao = new UserDao();
        try {
            this.user = userDao.findById(id);
        } catch (SQLException e) {
            this.errorMessage = "Erro ao buscar usuário";
            return false;
        }
        if (user == null) {
            this.errorMessage = "Usuário não encontrado.";
            return false;
        }
        this.id = id;
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.createdTime = user.getCreatedTime();
        this.profilePic = user.getProfilePic();
        return true;
    }

    public void updatePic(String filename) throws Exception {
        this.profilePic = filename;
        this.user.setProfilePic(filename);
        userDao.update(user);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getProfilePic() {
        return profilePic;
    }
}
