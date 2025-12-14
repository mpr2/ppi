package org.atividade05.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.atividade05.dao.CourseDao;
import org.atividade05.dao.LessonDao;
import org.atividade05.model.Course;
import org.atividade05.model.Lesson;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/lesson")
public class LessonController extends HttpServlet {
    private LessonDao lessonDao;
    private CourseDao courseDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.lessonDao = new LessonDao();
        this.courseDao = new CourseDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("course_id") == null) {
            try {
                List<Course> courses = courseDao.findAll();
                lessonDao.findById(1);
                req.setAttribute("courses", courses);
            } catch (SQLException e) {
                throw new ServletException("Erro ao buscar informações no banco de dados.", e);
            }
        }
        else {
            try {
                int courseId = Integer.parseInt(req.getParameter("course_id"));
                Course course = courseDao.findById(courseId);
                if (course == null) {
                    req.setAttribute("message", "Curso não existe.");
                }
                else {
                    req.setAttribute("course", course);
                    List<Lesson> lessons = lessonDao.findByCourseId(courseId);
                    req.setAttribute("lessons", lessons);
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao buscar informações no banco de dados.", e);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "ID inválido.");
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/lesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("create") != null) {
            try {
                Lesson lesson = new Lesson();
                lesson.setCourseId(Integer.parseInt(req.getParameter("course_id")));
                lesson.setTitle(req.getParameter("title"));
                lesson.setContent(req.getParameter("content"));
                lesson.setPosition(0);
                lessonDao.create(lesson);
            } catch (SQLException e) {
                throw new ServletException("Erro ao criar aula", e);
            }
        }

        else if (req.getParameter("change_content") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Lesson lesson = lessonDao.findById(id);
                if (lesson == null) {
                    req.setAttribute("message", "Aula não encontrada.");
                }
                else {
                    lesson.setContent(req.getParameter("new_content"));
                    lessonDao.update(lesson);
                    req.setAttribute("message", "Conteúdo alterado com sucesso.");
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao alterar o conteúdo.", e);
            }
        }

        else if (req.getParameter("remove") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                boolean wasDeleted = lessonDao.delete(id);
                if (!wasDeleted) {
                    req.setAttribute("message", "Aula não encontrada.");
                }
                else {
                    req.setAttribute("message", "Aula removida com sucesso.");
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao remover aula", e);
            }
        }
        
        try {
            int courseId = Integer.parseInt(req.getParameter("course_id"));
            Course course = courseDao.findById(courseId);
            if (course == null) {
                req.setAttribute("message", "Curso não existe.");
            }
            else {
                req.setAttribute("course", course);
                List<Lesson> lessons = lessonDao.findByCourseId(courseId);
                req.setAttribute("lessons", lessons);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar informações no banco de dados.", e);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/lesson.jsp").forward(req, resp);
    }
}
