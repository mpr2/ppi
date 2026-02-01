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

	// ================= CICLO DE VIDA =================

	// INIT - executado uma única vez quando o servlet é criado
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("LessonController inicializado.");
		this.lessonDao = new LessonDao();
		this.courseDao = new CourseDao();
	}

	// SERVICE - executado a cada requisição HTTP
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Service chamado em LessonController - Método HTTP: " + req.getMethod());
		super.service(req, resp); // mantém doGet e doPost funcionando
	}

	// DESTROY - executado quando o servlet é finalizado
	@Override
	public void destroy() {
		System.out.println("LessonController destruído.");
	}

	// ================= GET =================
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getParameter("course_id") == null) {
			try {
				List<Course> courses = courseDao.findAll();
				req.setAttribute("courses", courses);
			} catch (SQLException e) {
				throw new ServletException("Erro ao buscar informações no banco de dados.", e);
			}
		} else {
			try {
				int courseId = Integer.parseInt(req.getParameter("course_id"));
				Course course = courseDao.findById(courseId);

				if (course == null) {
					req.setAttribute("message", "Curso não existe.");
				} else {
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

	// ================= POST =================
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// -------- CREATE --------
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

		// -------- READ --------
		else if (req.getParameter("find") != null) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				Lesson lesson = lessonDao.findById(id);

				if (lesson == null) {
					req.setAttribute("message", "Aula não encontrada.");
				} else {
					req.setAttribute("message", "Aula encontrada.");
					req.setAttribute("lesson", lesson);
				}

			} catch (SQLException e) {
				throw new ServletException("Erro ao consultar aula.", e);
			}
		}

		// -------- UPDATE --------
		else if (req.getParameter("update") != null) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				Lesson lesson = lessonDao.findById(id);

				if (lesson == null) {
					req.setAttribute("message", "Aula não encontrada.");
				} else {
					lesson.setTitle(req.getParameter("title"));
					lesson.setContent(req.getParameter("content"));

					lessonDao.update(lesson);
					req.setAttribute("message", "Dados alterados com sucesso.");
				}

			} catch (SQLException e) {
				throw new ServletException("Erro ao alterar os dados.", e);
			}
		}

		// -------- DELETE --------
		else if (req.getParameter("remove") != null) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				boolean wasDeleted = lessonDao.delete(id);

				if (!wasDeleted) {
					req.setAttribute("message", "Aula não encontrada.");
				} else {
					req.setAttribute("message", "Aula removida com sucesso.");
				}

			} catch (SQLException e) {
				throw new ServletException("Erro ao remover aula", e);
			}
		}

		// Recarregar aulas do curso
		try {
			int courseId = Integer.parseInt(req.getParameter("course_id"));
			Course course = courseDao.findById(courseId);

			if (course == null) {
				req.setAttribute("message", "Curso não existe.");
			} else {
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
