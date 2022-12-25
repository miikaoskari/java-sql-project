package com.miika.studentmanager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class GraphicalSwing extends JFrame {
    private JFrame mainFrame;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JPanel graphPanel;
    private JPanel newStudentPanel;

    // alustetaan GUI ja lisätään elementit
    GraphicalSwing() throws SQLException {
        prepareGUI();
        prepareElements();
    }

    GraphicalSwing(int i) {

    }

    private void prepareGUI() {
        mainFrame = new JFrame("Student Manager");
        mainFrame.setSize(600,600);
        mainFrame.setLayout(new FlowLayout());

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        graphPanel = new JPanel();
        graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.PAGE_AXIS));

        newStudentPanel = new JPanel();
        newStudentPanel.setLayout(new BoxLayout(newStudentPanel, BoxLayout.PAGE_AXIS));

        mainFrame.add(tablePanel);
        mainFrame.add(buttonPanel);
        mainFrame.add(graphPanel);
        mainFrame.add(newStudentPanel);
        mainFrame.setVisible(true);

    }

    private void prepareElements() throws SQLException {
        String[] options = {"students","course","course_implementation","credit","student_degree"};
        JComboBox comboBox = new JComboBox(options);

        DefaultTableModel tableModel = new DefaultTableModel();
        final JTable table = new JTable(tableModel);
        JScrollPane tableScroll = (new JScrollPane(table));

        ApplicationMain a = new ApplicationMain();
        Connection conn = a.connect();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + comboBox.getSelectedItem());

        ResultSetMetaData metadata = resultSet.getMetaData();
        int columnCount = metadata.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            tableModel.addColumn(metadata.getColumnName(i));
        }

        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = resultSet.getObject(i);
            }
            tableModel.addRow(rowData);
        }

        JLabel label = new JLabel("syötä päivä vvvv-pp-kk, \njos tyhjä ohjelma muodostaa kaavion kaikista päivistä"
                ,JLabel.CENTER);
        JTextField dateTxt = new JTextField();

        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tableModel.fireTableStructureChanged();
            }
        });

        JButton saveButton = new JButton("Save to file");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        JButton readButton = new JButton("Read from file");
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        JLabel newStudentLabel = new JLabel("Enter firstname and lastname", JLabel.CENTER);
        JTextField newStudentText = new JTextField();

        JButton newStudentButton = new JButton("Add new student");
        newStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String queryNewStudent = "INSERT INTO students (first_name, last_name) VALUES (?,?)";
                    PreparedStatement statementNewStudent = conn.prepareStatement(queryNewStudent);
                    String[] i = (String[]) formatInsert(newStudentText.getText());
                    statementNewStudent.setString(1,i[0]);
                    statementNewStudent.setString(2,i[1]);
                    statementNewStudent.executeUpdate();
                    tableModel.addRow(formatInsert(newStudentText.getText()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });




        tablePanel.add(tableScroll);
        graphPanel.add(label);
        graphPanel.add(dateTxt);
        buttonPanel.add(comboBox);
        buttonPanel.add(showButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(readButton);
        newStudentPanel.add(newStudentLabel);
        newStudentPanel.add(newStudentText);
        buttonPanel.add(newStudentButton);
        mainFrame.setVisible(true);
    }

    public Object[] formatInsert(String text) {
        Object[] data = text.trim().split("\\s+");
        return data;
    }
}
