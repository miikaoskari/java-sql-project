package com.miika.studentmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class LoginSwing {
    private JFrame mainFrame;
    private JPanel mainPanel;

    LoginSwing() {
        prepareGUI();
        prepareElements();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Login Dialog");
        mainFrame.setSize(400,200);
        mainFrame.setLayout(new CardLayout());

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private void prepareElements() {
        JLabel usernameLabel = new JLabel("username:");
        JTextField usernameTxt = new JTextField();

        JLabel passwordLabel = new JLabel("password:");
        JPasswordField passwordTxt = new JPasswordField();

        JButton loginButton = new JButton("login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationMain a = new ApplicationMain();
                try {
                    a.auth(usernameTxt.getText(), String.valueOf(passwordTxt.getPassword()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton signupButton = new JButton("sign up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationMain a = new ApplicationMain();
                try {
                    a.signUp(usernameTxt.getText(), String.valueOf(passwordTxt.getPassword()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameTxt);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordTxt);
        mainPanel.add(loginButton);
        mainPanel.add(signupButton);
        mainFrame.setVisible(true);
    }

}

