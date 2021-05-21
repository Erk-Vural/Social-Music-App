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

public class SignInScreen extends JFrame {
    private static int ID;
    private JPanel newUserSignPanel;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField countryTextField;
    private JPasswordField passwordField;
    private JRadioButton premiumRadioButton;
    private JRadioButton payedRadioButton;
    private JButton signButton;
    private JButton backButton;


    public SignInScreen() {
        add(newUserSignPanel);
        setSize(600, 400);
        setTitle("Music App / SignIn");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                newUserSignPanel.setVisible(false);
                dispose();
            }
        });

        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String email = emailTextField.getText();
                char[] passwordChar = passwordField.getPassword();
                String password = new String(passwordChar);
                boolean isPremium = premiumRadioButton.isSelected();
                boolean isPayed = payedRadioButton.isSelected();
                String country = countryTextField.getText();

                if (signIn(name, email, password, isPremium, isPayed, country)) {
                    UserMainScreen userMainScreen = new UserMainScreen(ID);
                    userMainScreen.setVisible(true);
                    dispose();
                }
            }
        });

    }

    static boolean signIn(String name, String email, String password, boolean isPremium, boolean isPayed, String country) {
        try {
            Connection connection = DBConnection.getConnection();

            String IDSqlQuery;
            IDSqlQuery = "SELECT id FROM users WHERE id = (SELECT MAX(id))";

            PreparedStatement IDStatement = connection.prepareStatement(IDSqlQuery);
            ResultSet IDResultSet = IDStatement.executeQuery();

            while (IDResultSet.next()) {
                ID = IDResultSet.getInt(1) + 1;
            }

            String signSqlQuery;
            signSqlQuery = "INSERT INTO users(name,email,password,is_premium,is_payed,country)"
                    + "VALUES(?, ?, ?, ?, ?,?)";

            PreparedStatement signStatement = connection.prepareStatement(signSqlQuery);
            signStatement.setString(1, name);
            signStatement.setString(2, email);
            signStatement.setString(3, password);
            signStatement.setBoolean(4, isPremium);
            signStatement.setBoolean(5, isPayed);
            signStatement.setString(6, country);

            int userResultSet =  signStatement.executeUpdate();

            for (int i = 1; i <= 3; i++) {
                String playlistSqlQuery;
                playlistSqlQuery = "INSERT INTO  user_playlist(genre_id, playlist_id, user_id) " +
                        "VALUES (?,?,?)";

                PreparedStatement playlistStatement = connection.prepareStatement(playlistSqlQuery);
                playlistStatement.setInt(1, i);
                playlistStatement.setInt(2, i);
                playlistStatement.setInt(3, ID);

                int playlistResultSet = playlistStatement.executeUpdate();
            }

            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
