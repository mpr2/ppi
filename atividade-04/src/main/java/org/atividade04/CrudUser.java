package org.atividade04;

import java.sql.SQLException;
import java.util.List;

import org.atividade04.dao.UserDao;
import org.atividade04.model.User;

public class CrudUser {
    public static void crudUser() throws SQLException {
        UserDao userDao = new UserDao();

        System.out.println("=== Removendo usuários ===\n");

        List<User> userList = userDao.findAll();
        for (User user : userList) {
            userDao.delete(user.getId());
        }

        System.out.println("=== Criando usuários ===");

        User u1 = new User();
        u1.setUsername("joao");
        u1.setEmail("joao@example.com");
        u1.setPasswordHash("senha123");
        u1.setName("João Silva");

        User u2 = new User();
        u2.setUsername("maria");
        u2.setEmail("maria@example.com");
        u2.setPasswordHash("senha345");
        u2.setName("Maria Souza");

        userDao.create(u1);
        userDao.create(u2);

        System.out.println("Usuários criados.\n");

        // -----------------------------
        // Buscar todos os usuários
        // -----------------------------
        System.out.println("=== findAll() ===");
        List<User> users = userDao.findAll();
        for (User u : users) {
            System.out.println(u);
        }
        System.out.println();

        // -----------------------------
        // Buscar por ID
        // -----------------------------
        System.out.println("=== findById(1) ===");
        User foundById = userDao.findById(1);
        System.out.println(foundById != null ? foundById : "Usuário não encontrado");
        System.out.println();

        // -----------------------------
        // Buscar por Nome
        // -----------------------------
        System.out.println("=== findByName('João Silva') ===");
        User foundByName = userDao.findByName("João Silva");
        System.out.println(foundByName != null ? foundByName : "Usuário não encontrado");
        System.out.println();

        // -----------------------------
        // Atualizar usuário
        // -----------------------------
        System.out.println("=== atualizando usuário Maria Souza (mudando email) ===");
        User maria = userDao.findByName("Maria Souza");
        if (maria != null) {
            maria.setEmail("novoemail@example.com");
            boolean updated = userDao.update(maria);
            if (updated) {
                User updatedUser = userDao.findById(maria.getId());
                System.out.println("Usuário atualizado: " + updatedUser);
            } else {
                System.out.println("Usuário não atualizado.");
            }
        }
        System.out.println();

        // -----------------------------
        // Remover usuário
        // -----------------------------
        System.out.println("=== removendo usuário João Silva ===");
        User joao = userDao.findByName("João Silva");
        boolean deleted = userDao.delete(joao.getId());
        if (deleted) {
            System.out.println("Usuário removido.");
        } else {
            System.out.println("Usuário não encontrado");
        }
        System.out.println();

        // -----------------------------
        // Lista de usuários
        // -----------------------------
        System.out.println("=== Lista de usuários ===");
        users = userDao.findAll();
        for (User u : users) {
            System.out.println(u);
        }

        userDao.create(u1);
    }
}
