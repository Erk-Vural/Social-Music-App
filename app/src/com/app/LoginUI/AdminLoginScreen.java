package com.app.LoginUI;

import com.app.AdminUI.AdminMainScreen;
import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginScreen extends JFrame {
    private JPanel adminLoginPanel;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JPasswordField adminPasswordField;
    private JButton backButton;
    private JButton loginButton;


    public AdminLoginScreen() throws SQLException {
        add(adminLoginPanel);
        setSize(600, 400);
        setTitle("Music App / Admin Login");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String email = emailTextField.getText();
                char[] passwordChar = adminPasswordField.getPassword();
                String password = new String(passwordChar);

                if (isLogin(name, email, password)) {
                    AdminMainScreen adminMainScreen = new AdminMainScreen();
                    adminMainScreen.setVisible(true);
                    dispose();
                }

            }
        });
    }

    static boolean isLogin(String name, String email, String password) {
        try {
            Connection connection = DBConnection.getConnection();
            String loginSqlQuery;
            loginSqlQuery = "SELECT name,email,password FROM admin WHERE name = '" +
                    name +
                    "' AND email = '" +
                    email +
                    "' AND password = '" +
                    password +
                    "'";
            PreparedStatement loginStatement = connection.prepareStatement(loginSqlQuery);
            ResultSet resultSet = loginStatement.executeQuery();

            while (resultSet.next()) {

                connection.close();
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}


