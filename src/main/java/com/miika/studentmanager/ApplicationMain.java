package com.miika.studentmanager;

import java.sql.*;

public class ApplicationMain {

    private Connection conn;

    public static void main(String[] args) {
        // TODO: login system
        LoginSwing login = new LoginSwing();
    }

    public Connection connect() throws SQLException {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/students_schema", "root", "password");
        return conn;
    }

    public void auth(String username, String password) throws SQLException {
        conn = connect();

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

    public void signUp(String username, String password) throws SQLException {
        conn = connect();
        String queryNewStudent = "INSERT INTO login (username, password) VALUES (?,?)";
        PreparedStatement newUser = conn.prepareStatement(queryNewStudent);
        newUser.setString(1,username);
        newUser.setString(2,password);
        newUser.executeUpdate();
        conn.close();
    }
}