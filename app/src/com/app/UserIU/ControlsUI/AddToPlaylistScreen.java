package com.app.UserIU.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddToPlaylistScreen extends JFrame {


    private JPanel addPanel;
    private JTextField IDTextField;
    private JButton addButton;

    public AddToPlaylistScreen(int id) {
        add(addPanel);
        setSize(400, 300);
        setTitle("Music App / User ADD Song to Playlist");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDTextField.getText();
                int songID = Integer.parseInt(ID);

                addRecords(songID, id);
            }
        });

    }

    public void addRecords(int songID, int id) {
        int playlistID = 0;

        try {
            Connection connection = DBConnection.getConnection();

            String GetSongSqlQuery = "SELECT genre_id FROM songs WHERE id= '" + songID + "'";

            PreparedStatement GetSongStatement = connection.prepareStatement(GetSongSqlQuery);
            ResultSet GetResult = GetSongStatement.executeQuery();

            while (GetResult.next()) {
                playlistID = GetResult.getInt(1);
            }

            String SetSongSqlQuery = "INSERT INTO playlist_songs(playlist_id, song_id, user_id)"
                    + "VALUES(?, ?, ?)";

            PreparedStatement SetSongStatement = connection.prepareStatement(SetSongSqlQuery);

            SetSongStatement.setInt(1, playlistID);
            SetSongStatement.setInt(2, songID);
            SetSongStatement.setInt(3, id);

            int resultSet = SetSongStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
