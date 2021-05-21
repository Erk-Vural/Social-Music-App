package com.app.UserIU.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FollowUnfollowScreen extends JFrame {
    private JPanel followUnfollowPanel;
    private JTextField IDTextField;
    private JButton goButton;
    private JLabel followUnfollowLabel;

    public FollowUnfollowScreen(int followerID, int choice) {
        add(followUnfollowPanel);
        setSize(400, 400);
        setTitle("Music App / Follow - Unfollow");

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDTextField.getText();
                int userID = Integer.parseInt(ID);

                followUnfollow(userID, followerID, choice);
            }
        });
    }

    public void followUnfollow(int userID, int followerID, int choice) {
        try {
            Connection connection = DBConnection.getConnection();

            if (choice == 1) {
                followUnfollowLabel.setText("Write userID to Follow");

                String followSqlQuery = "INSERT INTO user_followers(user_id, follower_id)"
                        + "VALUES(?, ?)";

                PreparedStatement followStatement = connection.prepareStatement(followSqlQuery);

                followStatement.setInt(1, userID);
                followStatement.setInt(2, followerID);

                int FollowResult = followStatement.executeUpdate();

            } else if (choice == 2) {
                followUnfollowLabel.setText("Write userID to Unfollow");

                String UnfollowSqlQuery = "DELETE FROM user_followers WHERE " +
                        "follower_id = '" + followerID + "'" +
                        "AND user_id = '" + userID + "'";

                PreparedStatement UnfollowStatement = connection.prepareStatement(UnfollowSqlQuery);

                int UnfollowResult = UnfollowStatement.executeUpdate();
            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
