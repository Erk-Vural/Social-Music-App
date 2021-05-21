package com.app.UserIU.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddArtist extends JFrame {
    private JPanel artistPanel;
    private JTextField IDTextField;
    private JButton addArtistButton;

    public AddArtist(int userID) {
        add(artistPanel);
        setSize(400, 300);
        setTitle("Music App / User Add Artist");

        addArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDTextField.getText();
                int artistID = Integer.parseInt(ID);

                addArtist(artistID, userID);
            }
        });
    }

    public void addArtist(int artistID, int userID) {
        try {
            Connection connection = DBConnection.getConnection();

            String artistSqlQuery = "INSERT INTO user_artist(artist_song_id, user_id) "
                    + "VALUES(?, ?)";

            PreparedStatement artistStatement = connection.prepareStatement(artistSqlQuery);

            artistStatement.setInt(1, artistID);
            artistStatement.setInt(2, userID);

            int resultSet = artistStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
