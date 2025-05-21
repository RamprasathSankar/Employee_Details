package Swing;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeForm extends JFrame {
    // Components
    private JTextField empnoField, empnameField, ageField, salaryField;
    private JButton submitButton;

    public EmployeeForm() {
        // Frame Settings
        setTitle("Employee Entry Form");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Labels and Fields
        JLabel empnoLabel = new JLabel("Emp No:");
        empnoLabel.setBounds(10, 20, 80, 25);
        add(empnoLabel);

        empnoField = new JTextField();
        empnoField.setBounds(100, 20, 150, 25);
        add(empnoField);

        JLabel nameLabel = new JLabel("Emp Name:");
        nameLabel.setBounds(10, 60, 80, 25);
        add(nameLabel);

        empnameField = new JTextField();
        empnameField.setBounds(100, 60, 150, 25);
        add(empnameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 100, 80, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(100, 100, 150, 25);
        add(ageField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(10, 140, 80, 25);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(100, 140, 150, 25);
        add(salaryField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 180, 100, 30);
        add(submitButton);

        // Button Action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertEmployee();
            }
        });

        setVisible(true);
    }

    private void insertEmployee() {
        int empno = Integer.parseInt(empnoField.getText());
        String empname = empnameField.getText();
        int age = Integer.parseInt(ageField.getText());
        double salary = Double.parseDouble(salaryField.getText());

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306//employeeDatabase", "root", "ramkutty");
            String query = "INSERT INTO employee (empno, empname, age, salary) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, empno);
            pst.setString(2, empname);
            pst.setInt(3, age);
            pst.setDouble(4, salary);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
    	new EmployeeForm();
    }
}
