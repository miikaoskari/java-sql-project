package com.miika.studentmanager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    JTextField newStudentText = new JTextField();


    GraphicalSwing() throws SQLException {
        prepareGUI();
        prepareElements();
        createTable("students");
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

        JButton delButton = new JButton("Delete");
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ApplicationMain a = new ApplicationMain();
                    Connection conn = a.connect();

                    String queryDelStudent = "DELETE FROM students WHERE student_id=?";
                    String queryDelCourse = "DELETE FROM course WHERE course_id=?";
                    String queryDelCourseImp = "DELETE FROM course_implementation WHERE course_implementation_id=?";
                    String queryDelCredit = "DELETE FROM credit WHERE credit_id=?";
                    String queryDelStudentDeg = "DELETE FROM student_degree WHERE student_degree_id=?";


                    String[] i = (String[]) formatInsert(newStudentText.getText());

                    switch (comboBox.getSelectedItem().toString()) {
                        case "students":
                            PreparedStatement statementDelStudent = conn.prepareStatement(queryDelStudent);
                            statementDelStudent.setInt(1, Integer.parseInt(newStudentText.getText()));
                            statementDelStudent.executeUpdate();
                            break;
                        case "course":
                            PreparedStatement statementDelCourse = conn.prepareStatement(queryDelCourse);
                            statementDelCourse.setInt(1, Integer.parseInt(newStudentText.getText()));
                            statementDelCourse.executeUpdate();
                            break;
                        case "course_implementation":
                            PreparedStatement statementDelCourseImp = conn.prepareStatement(queryDelCourseImp);
                            statementDelCourseImp.setInt(1, Integer.parseInt(newStudentText.getText()));
                            statementDelCourseImp.executeUpdate();
                            break;
                        case "credit":
                            PreparedStatement statementDelCredit = conn.prepareStatement(queryDelCredit);
                            statementDelCredit.setInt(1, Integer.parseInt(newStudentText.getText()));
                            statementDelCredit.executeUpdate();
                            break;
                        case "student_degree":
                            PreparedStatement statementDelStudentDeg = conn.prepareStatement(queryDelStudentDeg);
                            statementDelStudentDeg.setInt(1, Integer.parseInt(newStudentText.getText()));
                            statementDelStudentDeg.executeUpdate();
                            break;
                    }
                    //statementDelStudent.setString(1,comboBox.getSelectedItem().toString());
                    //statementDelStudent.setString(1,tableModel.getColumnName(0));


                    refreshTable();
                    createTable(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        JButton newStudentButton = new JButton("Add");
        newStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ApplicationMain a = new ApplicationMain();
                    Connection conn = a.connect();
                    String queryNewStudent = "INSERT INTO students (first_name, last_name) VALUES (?,?)";
                    String queryNewCourse = "INSERT INTO course (course_name) VALUE (?)";
                    String queryNewCourseImp= "INSERT INTO course_implementation (starts, ends, points) VALUES (?,?,?)";
                    String queryNewCredit = "INSERT INTO credit (teacher, points, passed) VALUES (?,?,?)";
                    String queryNewStudentDegree = "INSERT INTO student_degree (degree, started, completed) VALUES (?,?,?)";

                    String[] i = (String[]) formatInsert(newStudentText.getText());

                    switch (comboBox.getSelectedItem().toString()) {
                        case "students":
                            PreparedStatement statementNewStudent = conn.prepareStatement(queryNewStudent);

                            //statementNewStudent.setString(1, comboBox.getSelectedItem().toString());
                            statementNewStudent.setString(1,i[0]);
                            statementNewStudent.setString(2,i[1]);
                            statementNewStudent.executeUpdate();
                            break;
                        case "course":
                            PreparedStatement statementNewCourse = conn.prepareStatement(queryNewCourse);

                            //statementNewCourse.setString(1, comboBox.getSelectedItem().toString());
                            statementNewCourse.setString(1,i[0]);
                            statementNewCourse.executeUpdate();
                            break;
                        case "course_implementation":
                            PreparedStatement statementNewCourseImp = conn.prepareStatement(queryNewCourseImp);

                            //statementNewCourseImp.setString(1, comboBox.getSelectedItem().toString());
                            statementNewCourseImp.setString(1,i[0]);
                            statementNewCourseImp.setString(2,i[1]);
                            statementNewCourseImp.setString(3,i[2]);
                            statementNewCourseImp.executeUpdate();
                            break;
                        case "credit":
                            PreparedStatement statementNewCredit = conn.prepareStatement(queryNewCredit);

                            //statementNewCredit.setString(1, comboBox.getSelectedItem().toString());
                            statementNewCredit.setString(1,i[0]);
                            statementNewCredit.setString(2,i[1]);
                            statementNewCredit.setString(3,i[2]);
                            statementNewCredit.executeUpdate();
                            break;
                        case "student_degree":
                            PreparedStatement statementNewStudentDeg = conn.prepareStatement(queryNewStudentDegree);

                            //statementNewStudentDeg.setString(1, comboBox.getSelectedItem().toString());
                            statementNewStudentDeg.setString(1,i[0]);
                            statementNewStudentDeg.setString(2,i[1]);
                            statementNewStudentDeg.setString(3,i[2]);
                            statementNewStudentDeg.executeUpdate();
                            break;
                    }

                    refreshTable();
                    createTable(Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        buttonPanel.add(comboBox);
        buttonPanel.add(showButton);
        buttonPanel.add(delButton);
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
                infoLabel.setText("For adding enter firstname and lastname. \n" +
                        "If removing enter student_id of selected row.");
                break;
            case "course":
                infoLabel.setText("Enter a course name. \n" +
                        "If removing enter course_id of selected row.");
                break;
            case "course_implementation":
                infoLabel.setText("Enter start date, end-date and points. \n" +
                        "If removing enter course_implementation_id of selected row.");
                break;
            case "credit":
                infoLabel.setText("Enter teacher, points and passed date. \n" +
                        "If removing enter credit_id of selected row.");
                break;
            case "student_degree":
                infoLabel.setText("Enter degree, started and completed. \n" +
                        "If removing enter student_degree_id of selected row");
                break;
            default:
                ErrorDialog n = new ErrorDialog("Invalid Option");
                break;
        }

        newStudentPanel.add(infoLabel);
        mainFrame.setVisible(true);
    }



}
