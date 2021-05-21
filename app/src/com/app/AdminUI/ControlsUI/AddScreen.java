package com.app.AdminUI.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddScreen extends JFrame {
    private JPanel addPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JButton addButton;

    public AddScreen(int choice) {
        add(addPanel);
        setSize(400, 600);
        setTitle("Music App / Admin ADD Data");
        setScreen(choice);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecords(choice);
            }
        });
    }

    public void setScreen(int choice) {
        if (choice == 1) {
            label1.setText("Album ID: ");
            label2.setText("Genre ID: ");
            label3.setText("Artist ID: ");
            label4.setText("Name: ");
            label5.setText("Release Date: ");
            label6.setText("Length: ");
            label7.setText("Play Count: ");

            textField3.setEnabled(true);
            textField4.setEnabled(true);
            textField5.setEnabled(true);
            textField6.setEnabled(true);
            textField7.setEnabled(true);

        } else if (choice == 2) {
            label1.setText("Name: ");
            label2.setText("Country: ");

        } else if (choice == 3) {
            label1.setText("Genre ID: ");
            label2.setText("Artist ID: ");
            label3.setText("Name: ");
            label4.setText("Release Date: ");

            textField3.setEnabled(true);
            textField4.setEnabled(true);
        }
    }

    public void addRecords(int choice) {
        try {
            Connection connection = DBConnection.getConnection();
            String sqlQuery = null;

            if (choice == 1) {

                String album = textField1.getText();
                String genre = textField2.getText();
                String artist = textField3.getText();
                String name = textField4.getText();
                String releaseDate = textField5.getText();
                String length = textField6.getText();
                String plays = textField7.getText();

                sqlQuery = "INSERT INTO songs(album_id, genre_id, artist_song_id, name, release_date, lenght, plays)"
                        + "VALUES(?, ?, ?, ?, ?,?, ?)";

                PreparedStatement songStatement = connection.prepareStatement(sqlQuery);
                songStatement.setString(1, album);
                songStatement.setString(2, genre);
                songStatement.setString(3, artist);
                songStatement.setString(4, name);
                songStatement.setString(5, releaseDate);
                songStatement.setString(6, length);
                songStatement.setString(7, plays);

                int resultSet = songStatement.executeUpdate();

            } else if (choice == 2) {

                String name = textField1.getText();
                String country = textField2.getText();

                sqlQuery = "INSERT INTO artists(name, country)"
                        + "VALUES(?, ?)";

                PreparedStatement artistStatement = connection.prepareStatement(sqlQuery);
                artistStatement.setString(1, name);
                artistStatement.setString(2, country);

                int resultSet = artistStatement.executeUpdate();

            } else if (choice == 3) {

                String genre = textField1.getText();
                String artist = textField2.getText();
                String name = textField3.getText();
                String releaseDate = textField4.getText();

                sqlQuery = "INSERT INTO albums(genre_id, artist_song_id, name, release_date)"
                        + "VALUES(?, ?, ?, ?)";

                PreparedStatement albumStatement = connection.prepareStatement(sqlQuery);
                albumStatement.setString(1, genre);
                albumStatement.setString(2, artist);
                albumStatement.setString(3, name);
                albumStatement.setString(4, releaseDate);

                int resultSet = albumStatement.executeUpdate();

            }
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
