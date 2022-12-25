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
import java.util.Objects;

public class GraphicalSwing extends JFrame {
    private JFrame mainFrame;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JPanel graphPanel;
    private JPanel newStudentPanel;
    JLabel infoLabel = new JLabel("", JLabel.CENTER);
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);
    JScrollPane tableScroll = (new JScrollPane(table));


    // alustetaan GUI ja lisätään elementit
    GraphicalSwing() throws SQLException {
        prepareGUI();
        prepareElements();
        //createTable();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Student Manager");
        mainFrame.setSize(800,600);
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

        ApplicationMain a = new ApplicationMain();
        Connection conn = a.connect();

        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    refreshTable();
                    createTable(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                    createInfoLabel(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        JButton saveButton = new JButton("Save to file");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });



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
                    refreshTable();
                    createTable(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        buttonPanel.add(comboBox);
        buttonPanel.add(showButton);
        buttonPanel.add(saveButton);
        newStudentPanel.add(newStudentText);
        buttonPanel.add(newStudentButton);
        mainFrame.setVisible(true);
    }

    public Object[] formatInsert(String text) {
        Object[] data = text.trim().split("\\s+");
        return data;
    }

    public void createTable(String tableName) throws SQLException {
        ApplicationMain a = new ApplicationMain();
        Connection conn = a.connect();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

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

        tablePanel.add(tableScroll);
        mainFrame.setVisible(true);
    }

    public void refreshTable() {
        tablePanel.remove(tableScroll);
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
    }

    public void createInfoLabel(String tableName) {
        switch (tableName) {
            case "students":
                infoLabel.setText("Enter firstname and lastname: ");
                break;
            case "course":
                infoLabel.setText("Enter a course name: ");
                break;
            case "course_implementation":
                infoLabel.setText("Enter start date, end-date and points: ");
                break;
            case "credit":
                infoLabel.setText("Enter teacher, points and passed date: ");
                break;
            case "student_degree":
                infoLabel.setText("Enter degree, started and completed: ");
                break;
            default:
                ErrorDialog n = new ErrorDialog("Invalid Option");
                break;
        }

        //JLabel infoLabel = new JLabel("Enter firstname and lastname", JLabel.CENTER);
        newStudentPanel.add(infoLabel);
        mainFrame.setVisible(true);
    }

}
