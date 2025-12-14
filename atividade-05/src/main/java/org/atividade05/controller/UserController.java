package org.atividade05.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.atividade05.dao.UserDao;
import org.atividade05.model.User;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar usuários no Banco de Dados.", e);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("create") != null) {
            try {
                User user = new User();
                user.setUsername(req.getParameter("username"));
                user.setEmail(req.getParameter("email"));
                user.setPasswordHash(BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt()));
                user.setName(req.getParameter("name"));
                userDao.create(user);
                req.setAttribute("message", "Usuário criado com sucesso.");
            } catch(SQLIntegrityConstraintViolationException e) {
                req.setAttribute("message", "Nome de usuário já existe! Tente novamente.");
            } catch (SQLException e) {
                throw new ServletException("Erro ao criar usuário", e);
            }
        }

        else if (req.getParameter("find") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                User user = userDao.findById(id);
                if (user == null) {
                    req.setAttribute("message", "Usuário não encontrado.");
                }
                else {
                    req.setAttribute("message", "Usuário encontrado.");
                    req.setAttribute("user", user);
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao consultar usuário.", e);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "ID inválido. Tente novamente.");
            }
        }

        else if (req.getParameter("remove") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                boolean wasDeleted = userDao.delete(id);
                if (!wasDeleted) {
                    req.setAttribute("message", "Usuário não encontrado");
                }
                else {
                    req.setAttribute("message", "Usuário removido com sucesso");
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao remover usuário", e);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "ID inválido. Tente novamente.");
            }
        }

        else if (req.getParameter("update") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                User user = userDao.findById(id);
                if (user == null) {
                    req.setAttribute("message", "Usuário não encontrado.");
                }
                else if(!BCrypt.checkpw(req.getParameter("current_password"), user.getPasswordHash())) {
                    req.setAttribute("message", "Senha incorreta.");
                }
                else {
                    user.setUsername(req.getParameter("username"));
                    user.setEmail(req.getParameter("email"));
                    user.setPasswordHash(BCrypt.hashpw(req.getParameter("new_password"), BCrypt.gensalt()));
                    user.setName(req.getParameter("name"));
                    userDao.update(user);
                    req.setAttribute("message", "Dados alterados com sucesso.");
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao alterar dados.", e);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "ID inválido. Tente novamente.");
            }
        }

        try {
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar usuários no Banco de Dados.", e);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(req, resp);
    }
}
