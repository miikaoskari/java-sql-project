package com.miika.studentmanager;

import javax.swing.*;

public class ErrorDialog {
    private JFrame mainFrame;
    private JOptionPane optionPane;
    private String error;

    ErrorDialog(String error) {
        this.error = error;
        createError();
    }

    private void createError() {
        JOptionPane.showMessageDialog(mainFrame, this.error);
    }
}
