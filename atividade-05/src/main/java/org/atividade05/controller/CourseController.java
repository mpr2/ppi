package org.atividade05.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.atividade05.dao.CourseDao;
import org.atividade05.dao.UserDao;
import org.atividade05.model.Course;
import org.atividade05.model.User;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/course")
public class CourseController extends HttpServlet {
    private CourseDao courseDao;
    private UserDao userDao;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.courseDao = new CourseDao();
        this.userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
            List<Course> courses = courseDao.findAll();
            req.setAttribute("courses", courses);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar informações no Banco de Dados.", e);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("create") != null) {
            try {
                Course course = new Course();
                course.setInstructorId(Integer.parseInt(req.getParameter("instructor_id")));
                course.setTitle(req.getParameter("title"));
                course.setDescription(req.getParameter("description"));
                courseDao.create(course);
                req.setAttribute("message", "Usuário criado com sucesso.");
            } catch(SQLIntegrityConstraintViolationException e) {
                req.setAttribute("message", "Instrutor não existe. Tente novamente.");
            } catch (SQLException e) {
                throw new ServletException("Erro ao criar usuário", e);
            }
        }

        else if (req.getParameter("change_description") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Course course = courseDao.findById(id);
                if (course == null) {
                    req.setAttribute("message", "Curso não encontrado.");
                }
                else {
                    course.setDescription(req.getParameter("new_description"));
                    courseDao.update(course);
                    req.setAttribute("message", "Descrição alterada com sucesso.");
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao alterar Descrição.", e);
            }
        }

        else if (req.getParameter("remove") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                boolean wasDeleted = courseDao.delete(id);
                if (!wasDeleted) {
                    req.setAttribute("message", "Curso não encontrado");
                }
                else {
                    req.setAttribute("message", "Curso removido com sucesso");
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao remover curso", e);
            }
        }

        try {
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
            List<Course> courses = courseDao.findAll();
            req.setAttribute("courses", courses);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar informações no Banco de Dados.", e);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/course.jsp").forward(req, resp);
    }
}
