package com.app.UserIU.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAlbum extends JFrame {
    private JPanel albumPanel;
    private JTextField IDTextField;
    private JButton addButton;

    public AddAlbum(int userID) {
        add(albumPanel);
        setSize(400, 300);
        setTitle("Music App / User Add Album");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDTextField.getText();
                int albumID = Integer.parseInt(ID);

                addAlbum(albumID, userID);
            }
        });
    }

    public void addAlbum(int albumID, int userID) {
        try {
            Connection connection = DBConnection.getConnection();

            String albumSqlQuery = "INSERT INTO user_album(album_id, user_id) "
                    + "VALUES(?, ?)";

            PreparedStatement albumStatement = connection.prepareStatement(albumSqlQuery);

            albumStatement.setInt(1, albumID);
            albumStatement.setInt(2, userID);

            int resultSet = albumStatement.executeUpdate();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
