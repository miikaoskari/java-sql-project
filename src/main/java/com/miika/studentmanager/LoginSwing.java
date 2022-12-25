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
    private JPanel buttonPanel;
    private JPanel textPanel;

    LoginSwing() {
        prepareGUI();
        prepareElements();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Login Dialog");
        mainFrame.setSize(400,200);
        mainFrame.setLayout(new BorderLayout());

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        mainFrame.add(textPanel,BorderLayout.NORTH);
        mainFrame.add(buttonPanel,BorderLayout.SOUTH);
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
                //a.auth(usernameTxt.getText(), String.valueOf(passwordTxt.getPassword()));
            }
        });

        textPanel.add(usernameLabel);
        textPanel.add(usernameTxt);
        textPanel.add(passwordLabel);
        textPanel.add(passwordTxt);
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

    }

}

