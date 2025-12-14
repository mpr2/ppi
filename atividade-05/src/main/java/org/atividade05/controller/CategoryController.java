package org.atividade05.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.atividade05.dao.CategoryDao;
import org.atividade05.model.Category;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private CategoryDao categoryDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.categoryDao = new CategoryDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<Category> categories = categoryDao.findAll();
            req.setAttribute("categories", categories);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar categorias no Banco de Dados.", e);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // -------- CREATE --------
        if (req.getParameter("create") != null) {
            try {
                Category category = new Category();
                category.setId(req.getParameter("id"));
                category.setName(req.getParameter("name"));
                category.setDescription(req.getParameter("description"));

                categoryDao.create(category);
                req.setAttribute("message", "Categoria criada com sucesso.");
            }
            catch (SQLIntegrityConstraintViolationException e) {
                req.setAttribute("message", "ID já existe! Escolha outro.");
            }
            catch (SQLException e) {
                throw new ServletException("Erro ao criar categoria", e);
            }
        }

        // -------- DELETE --------
        else if (req.getParameter("remove") != null) {
            try {
                String id = req.getParameter("id");

                boolean wasDeleted = categoryDao.delete(id);

                if (!wasDeleted) {
                    req.setAttribute("message", "Categoria não encontrada.");
                } else {
                    req.setAttribute("message", "Categoria removida com sucesso.");
                }
            } catch (SQLException e) {
                throw new ServletException("Erro ao remover categoria", e);
            }
        }

        // -------- UPDATE --------
        else if (req.getParameter("update") != null) {
            try {
                String id = req.getParameter("id");
                Category category = categoryDao.findById(id);

                if (category == null) {
                    req.setAttribute("message", "Categoria não encontrada.");
                } else {
                    category.setName(req.getParameter("name"));
                    category.setDescription(req.getParameter("description"));

                    categoryDao.update(category);
                    req.setAttribute("message", "Categoria atualizada com sucesso.");
                }

            } catch (SQLException e) {
                throw new ServletException("Erro ao atualizar categoria", e);
            }
        }

        // Recarregar lista de categorias
        try {
            List<Category> categories = categoryDao.findAll();
            req.setAttribute("categories", categories);
        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar categorias no Banco de Dados.", e);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/category.jsp").forward(req, resp);
    }
}
