package org.atividade08.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.atividade08.dao.CourseDao;
import org.atividade08.dao.UserDao;
import org.atividade08.model.Course;
import org.atividade08.model.User;

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

	// ================= CICLO DE VIDA =================

	// INIT - executado uma única vez quando o servlet é criado
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("CourseController inicializado.");
		this.courseDao = new CourseDao();
		this.userDao = new UserDao();
	}

	// SERVICE - executado a cada requisição HTTP
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Service chamado em CourseController - Método HTTP: " + req.getMethod());
		super.service(req, resp); // mantém doGet e doPost funcionando
	}

	// DESTROY - executado quando o servlet é finalizado
	@Override
	public void destroy() {
		System.out.println("CourseController destruído.");
	}

	// ================= GET =================
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

	// ================= POST =================
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// -------- CREATE --------
		if (req.getParameter("create") != null) {
			try {
				Course course = new Course();
				course.setInstructorId(Integer.parseInt(req.getParameter("instructor_id")));
				course.setTitle(req.getParameter("title"));
				course.setDescription(req.getParameter("description"));
				course.setCategoryId(req.getParameter("category_id"));

				courseDao.create(course);
				req.setAttribute("message", "Curso criado com sucesso.");

			} catch (SQLIntegrityConstraintViolationException e) {
				req.setAttribute("message", "Instrutor ou categoria não existe. Tente novamente.");
			} catch (SQLException e) {
				throw new ServletException("Erro ao criar curso", e);
			}
		}

		// -------- READ --------
		else if (req.getParameter("find") != null) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				Course course = courseDao.findById(id);

				if (course == null) {
					req.setAttribute("message", "Curso não encontrado.");
				} else {
					req.setAttribute("message", "Curso encontrado.");
					req.setAttribute("course", course);
				}

			} catch (SQLException e) {
				throw new ServletException("Erro ao consultar curso.", e);
			}
		}

		// -------- UPDATE --------
		else if (req.getParameter("update") != null) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				Course course = courseDao.findById(id);

				if (course == null) {
					req.setAttribute("message", "Curso não encontrado.");
				} else {
					course.setTitle(req.getParameter("title"));
					course.setDescription(req.getParameter("description"));
					course.setCategoryId(req.getParameter("category_id"));

					courseDao.update(course);
					req.setAttribute("message", "Dados alterados com sucesso.");
				}

			} catch (SQLException e) {
				throw new ServletException("Erro ao alterar dados.", e);
			}
		}

		// -------- DELETE --------
		else if (req.getParameter("remove") != null) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				boolean wasDeleted = courseDao.delete(id);

				if (!wasDeleted) {
					req.setAttribute("message", "Curso não encontrado.");
				} else {
					req.setAttribute("message", "Curso removido com sucesso.");
				}

			} catch (SQLException e) {
				throw new ServletException("Erro ao remover curso", e);
			}
		}

		// Recarregar dados
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
