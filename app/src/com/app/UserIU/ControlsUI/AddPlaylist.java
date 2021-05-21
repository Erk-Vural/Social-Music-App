package com.app.UserIU.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPlaylist extends JFrame {
    private JPanel playlistPanel;
    private JTextField IDTextField;
    private JButton addPlaylistButton;
    private JTextField genreIDTextField;

    public AddPlaylist(int userID) {
        add(playlistPanel);
        setSize(400, 300);
        setTitle("Music App / User Add Playlist");

        addPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDTextField.getText();
                int playlistID = Integer.parseInt(ID);

                String gID = genreIDTextField.getText();
                int genreID = Integer.parseInt(gID);

                addPlaylist(playlistID, userID, genreID);
            }
        });
    }

    public void addPlaylist(int playlistID, int userID, int genreID) {
        try {
            Connection connection = DBConnection.getConnection();

            String playlistSqlQuery = "INSERT INTO user_playlist(genre_id,playlist_id, user_id) "
                    + "VALUES(?, ?, ?)";

            PreparedStatement playlistStatement = connection.prepareStatement(playlistSqlQuery);

            playlistStatement.setInt(1, genreID);
            playlistStatement.setInt(2, playlistID);
            playlistStatement.setInt(3, userID);

            int resultSet = playlistStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
