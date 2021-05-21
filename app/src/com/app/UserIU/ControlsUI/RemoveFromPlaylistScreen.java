package com.app.UserIU.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveFromPlaylistScreen extends JFrame {
    private JPanel removePanel;
    private JTextField IDTextField;
    private JButton removeSongButton;

    public RemoveFromPlaylistScreen(int userID) {
        add(removePanel);
        setSize(400, 300);
        setTitle("Music App / User Remove Song");

        removeSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDTextField.getText();
                int songID = Integer.parseInt(ID);

                removeSong(userID, songID);
            }
        });
    }

    public void removeSong(int userID, int songID) {
        try {
            Connection connection = DBConnection.getConnection();
            String removeSqlQuery = null;

            removeSqlQuery = "DELETE FROM playlist_songs WHERE song_id = '" + songID + "'" +
                    "AND user_id = '" + userID + "'";

            PreparedStatement removeStatement = connection.prepareStatement(removeSqlQuery);
            int resultSet = removeStatement.executeUpdate();

            connection.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
