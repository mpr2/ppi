package org.atividade04;

import java.sql.SQLException;
import java.util.List;

import org.atividade04.dao.CourseDao;
import org.atividade04.dao.LessonDao;
import org.atividade04.dao.UserDao;
import org.atividade04.model.Course;
import org.atividade04.model.Lesson;
import org.atividade04.model.User;

public class CrudLesson {
    public static void crudLesson() throws SQLException {
        UserDao userDao = new UserDao();
        CourseDao courseDao = new CourseDao();
        LessonDao lessonDao = new LessonDao();

        // buscando curso de Maria para usar de base para criar as aulas
        User maria = userDao.findByName("Maria Souza");
        Course mariaCourse = courseDao.findByInstructorId(maria.getId()).get(0);

        System.out.println("=== Removendo aulas de Maria ===\n");

        // buscando aulas do curso de maria e removendo uma a uma para reinicializar a tabela
        List<Lesson> lessonList = lessonDao.findByCourseId(mariaCourse.getId());
        for (Lesson lesson : lessonList) {
            lessonDao.delete(lesson.getId());
        }

        System.out.println("=== Criando aulas para o curso de Maria ===");

        Lesson l1 = new Lesson();
        l1.setCourseId(mariaCourse.getId());
        l1.setTitle("Aula 1");
        l1.setContent("Conteúdo da aula 1.");
        l1.setPosition(1);

        Lesson l2 = new Lesson();
        l2.setCourseId(mariaCourse.getId());
        l2.setTitle("Aula 2");
        l2.setContent("Conteúdo da aula 2.");
        l2.setPosition(2);

        lessonDao.create(l1);
        lessonDao.create(l2);

        System.out.println("Aulas criadas.\n");

        // -----------------------------
        // Buscar todas as aulas do curso de Maria
        // -----------------------------
        System.out.println("=== findByInstructorId() ===");
        List<Lesson> lessons = lessonDao.findByCourseId(mariaCourse.getId());
        for (Lesson l : lessons) {
            System.out.println(l);
        }
        System.out.println();

        // -----------------------------
        // Buscar por ID
        // -----------------------------
        System.out.println("=== findById(1) ===");
        Lesson foundById = lessonDao.findById(1);
        System.out.println(foundById != null ? foundById : "Aula não encontrada");
        System.out.println();

        // -----------------------------
        // Atualizar aula
        // -----------------------------
        System.out.println("=== atualizando aula (mudando conteúdo) ===");

        // buscando primeira aula
        Lesson lesson = lessons.get(0);

        lesson.setContent("Novo conteúdo.");
        boolean updated = lessonDao.update(lesson);
        if (updated) {
            Lesson updatedLesson = lessonDao.findById(lesson.getId());
            System.out.println("Aula atualizada: " + updatedLesson);
        } else {
            System.out.println("Aula não atualizada.");
        }
        System.out.println();

        // -----------------------------
        // Remover aula
        // -----------------------------
        System.out.println("=== removendo aula ===");

        // buscando segunda aula
        lesson = lessons.get(1);

        boolean deleted = lessonDao.delete(lesson.getId());
        if (deleted) {
            System.out.println("Aula removida.");
        } else {
            System.out.println("Aula não encontrada");
        }
        System.out.println();

        // -----------------------------
        // Lista de aulas
        // -----------------------------
        System.out.println("=== Lista de aulas de Maria ===");
        lessons = lessonDao.findByCourseId(mariaCourse.getId());
        for (Lesson l : lessons) {
            System.out.println(l);
        }
    }
}
