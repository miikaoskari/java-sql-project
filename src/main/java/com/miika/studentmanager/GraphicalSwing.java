package com.miika.studentmanager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicalSwing extends JFrame {
    private JFrame mainFrame;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JPanel graphPanel;
    private JPanel newSpendPanel;
    private JOptionPane errorDialog;

    // alustetaan GUI ja lisätään elementit
    GraphicalSwing() {
        prepareGUI();
        prepareElements();

    }

    GraphicalSwing(int i) {
        if(i == 1) {
            prepareGUI();
            prepareElements();
        } else {
            errorDialog.showMessageDialog(null,"Login failed..." );
        }
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

        newSpendPanel = new JPanel();
        newSpendPanel.setLayout(new BoxLayout(newSpendPanel, BoxLayout.PAGE_AXIS));

        mainFrame.add(tablePanel);
        mainFrame.add(buttonPanel);
        mainFrame.add(graphPanel);
        mainFrame.add(newSpendPanel);
        mainFrame.setVisible(true);

    }

    private void prepareElements() {
        String[] columns = new String[] {"Name", "", "Price", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        final JTable table = new JTable(tableModel);
        JScrollPane tableScroll = (new JScrollPane(table));



        JLabel label = new JLabel("syötä päivä vvvv-pp-kk, \njos tyhjä ohjelma muodostaa kaavion kaikista päivistä"
                ,JLabel.CENTER);
        JTextField dateTxt = new JTextField();

        JButton showButton = new JButton("Show Diagram");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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

        JLabel newSpendLabel = new JLabel("syötä kategoria, kommentti, hinta ja päivämäärä (vvvv-pp-kk)", JLabel.CENTER);
        JTextField newSpendField = new JTextField();

        JButton newSpendButton = new JButton("Create Spend");
        newSpendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        tablePanel.add(tableScroll);
        graphPanel.add(label);
        graphPanel.add(dateTxt);
        buttonPanel.add(showButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(readButton);
        newSpendPanel.add(newSpendLabel);
        newSpendPanel.add(newSpendField);
        buttonPanel.add(newSpendButton);
        mainFrame.setVisible(true);
    }

    public Object[] formatInsert(String text) {
        Object[] data = text.trim().split("\\s*,\\s*");
        return data;
    }
}
