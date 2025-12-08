package org.atividade04;

import java.sql.SQLException;
import java.util.List;

import org.atividade04.dao.CourseDao;
import org.atividade04.dao.UserDao;
import org.atividade04.model.Course;
import org.atividade04.model.User;

public class CrudCourse {
    public static void crudCourse() throws SQLException {
        CourseDao courseDao = new CourseDao();
        UserDao userDao = new UserDao();

        System.out.println("=== Removendo cursos ===\n");

        List<Course> courseList = courseDao.findAll();
        for (Course course : courseList) {
            courseDao.delete(course.getId());
        }

        System.out.println("=== Criando cursos ===");

        User maria = userDao.findByName("Maria Souza");
        User joao = userDao.findByName("Joao Silva");

        Course c1 = new Course();
        c1.setInstructorId(maria.getId());
        c1.setTitle("Curso de Maria");
        c1.setDescription("Descrição do Curso de Maria.");

        Course c2 = new Course();
        c2.setInstructorId(joao.getId());
        c2.setTitle("Curso de João");
        c2.setDescription("Descrição do Curso de João.");

        courseDao.create(c1);
        courseDao.create(c2);

        System.out.println("Cursos criados.\n");

        // -----------------------------
        // Buscar todos os cursos
        // -----------------------------
        System.out.println("=== findAll() ===");
        List<Course> courses = courseDao.findAll();
        for (Course c : courses) {
            System.out.println(c);
        }
        System.out.println();

        // -----------------------------
        // Buscar por ID
        // -----------------------------
        System.out.println("=== findById(1) ===");
        Course foundById = courseDao.findById(1);
        System.out.println(foundById != null ? foundById : "Curso não encontrado");
        System.out.println();

        // -----------------------------
        // Atualizar curso
        // -----------------------------
        System.out.println("=== atualizando curso de Maria Souza (mudando título) ===");
        List<Course> mariaCourses = courseDao.findByInstructorId(maria.getId());
        Course mariaCourse = mariaCourses.get(0);

        mariaCourse.setTitle("Novo Título");
        boolean updated = courseDao.update(mariaCourse);
        if (updated) {
            Course updatedCourse = courseDao.findById(mariaCourse.getId());
            System.out.println("Curso atualizado: " + updatedCourse);
        } else {
            System.out.println("Curso não atualizado.");
        }
        System.out.println();

        // -----------------------------
        // Remover curso
        // -----------------------------
        System.out.println("=== removendo curso de João Silva ===");
        List<Course> joaoCourses = courseDao.findByInstructorId(joao.getId());
        Course joaoCourse = joaoCourses.get(0);

        boolean deleted = courseDao.delete(joaoCourse.getId());
        if (deleted) {
            System.out.println("Curso removido.");
        } else {
            System.out.println("Curso não encontrado");
        }
        System.out.println();

        // -----------------------------
        // Lista de cursos
        // -----------------------------
        System.out.println("=== Lista de cursos ===");
        courses = courseDao.findAll();
        for (Course c : courses) {
            System.out.println(c);
        }
    }
}
