package org.atividade04;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("===== TABELA USUÁRIOS =====\n\n");
            CrudUser.crudUser();

            System.out.println("\n\n\n\n===== TABELA CURSOS =====\n\n");
            CrudCourse.crudCourse();

            System.out.println("\n\n\n\n===== TABELA AULAS =====\n\n");
            CrudLesson.crudLesson();

            System.out.println("\n");
        } catch (SQLException e) {
            throw new RuntimeException("erro", e);
        }
    }
}
