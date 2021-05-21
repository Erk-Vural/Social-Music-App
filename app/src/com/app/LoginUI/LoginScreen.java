package com.app.LoginUI;

import com.app.DBConnection;
import com.app.UserIU.UserMainScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginScreen extends JFrame {

    int ID = 0;
    private JPanel loginPanel;
    private JTextField emailTextField;
    private JPasswordField userPasswordField;
    private JButton loginButton;
    private JButton adminLoginButton;
    private JButton signInButton;

    public LoginScreen() {
        add(loginPanel);
        setSize(600, 400);
        setTitle("Music App / Login Screen");

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLoginScreen adminLoginScreen = null;
                try {
                    adminLoginScreen = new AdminLoginScreen();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                adminLoginScreen.setVisible(true);
                dispose();
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignInScreen signInScreen = new SignInScreen();
                signInScreen.setVisible(true);
                dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();
                char[] passwordChar = userPasswordField.getPassword();
                String password = new String(passwordChar);

                if (isLogin(email, password)) {
                    UserMainScreen userMainScreen = new UserMainScreen(ID);
                    userMainScreen.setVisible(true);
                    dispose();
                }
            }
        });
    }

    public boolean isLogin(String email, String password) {
        try {
            Connection connection = DBConnection.getConnection();

            String IDSqlQuery;
            IDSqlQuery = "SELECT id FROM users WHERE email = '" + email + "'";

            PreparedStatement IDStatement = connection.prepareStatement(IDSqlQuery);
            ResultSet IDResultSet = IDStatement.executeQuery();

            while (IDResultSet.next()) {
                ID = IDResultSet.getInt(1);
            }

            String loginSqlQuery;
            loginSqlQuery = "SELECT email,password FROM users WHERE email = '" +
                    email +
                    "' AND password = '" +
                    password +
                    "'";

            PreparedStatement loginStatement = connection.prepareStatement(loginSqlQuery);
            ResultSet loginResultSet = loginStatement.executeQuery();

            while (loginResultSet.next()) {

                connection.close();
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
