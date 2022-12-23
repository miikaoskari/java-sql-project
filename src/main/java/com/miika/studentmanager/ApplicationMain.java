package com.miika.studentmanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ApplicationMain {

    public static void main(String[] args) {
        // TODO: login system
        LoginSwing login = new LoginSwing();

        //GraphicalSwing s = new GraphicalSwing();

    }

    public boolean auth(String username, String password) {
        Login l = new Login();
        l.setUsername(username);
        l.setPassword(password);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Login> query = entityManager.createQuery("SELECT l FROM Login l WHERE l.username = :username",
                Login.class);
        query.setParameter("username", username);

        Login login = query.getSingleResult();
        if (login == null) {
            return false;
        }

        return true;
    }
}