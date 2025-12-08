package org.atividade04;

import java.sql.SQLException;
import java.util.List;

import org.atividade04.dao.CategoryDao;
import org.atividade04.model.Category;

public class CrudCategory {
    public static void crudCategory() throws SQLException {
        CategoryDao categoryDao = new CategoryDao();

        System.out.println("=== Removendo categorias ===\n");

        // Busca todas as categorias e remove uma por uma para reinicializar a tabela
        List<Category> categoryList = categoryDao.findAll();
        for (Category category : categoryList) {
            categoryDao.delete(category.getId());
        }

        System.out.println("=== Criando categorias ===");

        Category c1 = new Category();
        c1.setId("TECH");
        c1.setName("Tecnologia");
        c1.setDescription("Descrição da categoria Tecnologia.");

        Category c2 = new Category();
        c2.setId("MKT");
        c2.setName("Marketing");
        c2.setDescription("Descrição da categoria Marketing.");

        categoryDao.create(c1);
        categoryDao.create(c2);

        System.out.println("Categorias criadas.\n");

        // -----------------------------
        // Buscar todas as categorias
        // -----------------------------
        System.out.println("=== findAll() ===");
        List<Category> categories = categoryDao.findAll();
        for (Category c : categories) {
            System.out.println(c);
        }
        System.out.println();

        // -----------------------------
        // Buscar por ID
        // -----------------------------
        System.out.println("=== findById(TECH) ===");
        Category foundById = categoryDao.findById("TECH");
        System.out.println(foundById != null ? foundById : "Categoria não encontrada");
        System.out.println();

        // -----------------------------
        // Atualizar categoria
        // -----------------------------
        System.out.println("=== atualizando categoria de Tecnologia (mudando descrição) ===");
        Category tech = categoryDao.findById("TECH");
        if (tech != null) {
            tech.setDescription("Nova descrição.");
            boolean updated = categoryDao.update(tech);
            if (updated) {
                Category updatedCategory = categoryDao.findById(tech.getId());
                System.out.println("Categoria atualizada: " + updatedCategory);
            } else {
                System.out.println("Categoria não atualizada.");
            }
        }
        System.out.println();

        // -----------------------------
        // Remover categoria
        // -----------------------------
        System.out.println("=== removendo categoria de Marketing ===");
        Category mkt = categoryDao.findById("MKT");
        boolean deleted = categoryDao.delete(mkt.getId());
        if (deleted) {
            System.out.println("Categoria removida.");
        } else {
            System.out.println("Categoria não encontrada");
        }
        System.out.println();

        // -----------------------------
        // Lista de categorias
        // -----------------------------
        System.out.println("=== Lista de categorias ===");
        categories = categoryDao.findAll();
        for (Category c : categories) {
            System.out.println(c);
        }

        // recriando categoria de Marketing
        categoryDao.create(c2);
    }
}
