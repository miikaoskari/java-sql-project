package com.miika.studentmanager;

import org.hibernate.annotations.common.util.impl.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.sql.*;
import java.util.List;

public class ApplicationMain {

    private Connection conn;

    public static void main(String[] args) {
        // TODO: login system
        LoginSwing login = new LoginSwing();

        //GraphicalSwing s = new GraphicalSwing();

    }

    public void auth(String username, String password) throws SQLException {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/students_schema", "root", "password");

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM login");

        while (rs.next()) {
            String dbUsername = rs.getString("username");
            String dbPassword = rs.getString("password");
            if(dbUsername.contentEquals(username) && dbPassword.contentEquals(password)) {
                GraphicalSwing n = new GraphicalSwing();
            } else {
                ErrorDialog e = new ErrorDialog("Username or password is not correct.");
            }
        }
        conn.close();

    }
}