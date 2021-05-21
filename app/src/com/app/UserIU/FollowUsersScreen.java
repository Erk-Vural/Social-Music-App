package com.app.UserIU;

import com.app.DBConnection;
import com.app.UserIU.ControlsUI.FollowUnfollowScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowUsersScreen extends JFrame {

    Object[] premiumUserColumns = {"name", "email", "country"};
    Object[] premiumUserRows = new Object[3];
    private JPanel userFollowUsersPanel;
    private JTable premiumUserTable;
    private JButton unfollowUserButton;
    private JButton followUserButton;

    public FollowUsersScreen(int ID) {
        add(userFollowUsersPanel);
        setSize(600, 600);
        setVisible(true);

        printPremiumUser();

        followUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FollowUnfollowScreen followUnfollowScreen = new FollowUnfollowScreen(ID, 1);
                followUnfollowScreen.setVisible(true);
            }
        });

        unfollowUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FollowUnfollowScreen followUnfollowScreen = new FollowUnfollowScreen(ID, 2);
                followUnfollowScreen.setVisible(true);
            }
        });
    }

    public void printPremiumUser() {
        try {
            Connection connection = DBConnection.getConnection();
            String premiumUserSqlQuery;

            premiumUserTable.setFillsViewportHeight(true);

            premiumUserSqlQuery = "SELECT * FROM users WHERE is_premium = true AND is_payed = true";

            PreparedStatement premiumUserStatement = connection.prepareStatement(premiumUserSqlQuery);
            ResultSet resultSet = premiumUserStatement.executeQuery();

            DefaultTableModel premiumUserModel = new DefaultTableModel();
            premiumUserModel.setColumnIdentifiers(premiumUserRows);

            while (resultSet.next()) {
                premiumUserRows[0] = resultSet.getString("name");
                premiumUserRows[1] = resultSet.getString("email");
                premiumUserRows[2] = resultSet.getString("country");


                premiumUserModel.addRow(premiumUserRows);
                premiumUserTable.setModel(premiumUserModel);
            }

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
