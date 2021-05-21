package com.app.AdminUI.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateScreen extends JFrame {

    String ID;
    private JPanel updatePanel;
    private JTextField IDTextField;
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
    private JButton updateButton;
    private JButton goButton;

    public UpdateScreen(int choice) {
        add(updatePanel);
        setSize(400, 800);
        setTitle("Music App / Admin UPDATE Data");


        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ID = IDTextField.getText();
                setScreen(choice);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRecord(choice, ID);
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

            textField1.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            textField4.setEnabled(true);
            textField5.setEnabled(true);
            textField6.setEnabled(true);
            textField7.setEnabled(true);

        } else if (choice == 2) {
            label1.setText("Name: ");
            label2.setText("Country: ");

            textField1.setEnabled(true);
            textField2.setEnabled(true);

        } else if (choice == 3) {
            label1.setText("Genre ID: ");
            label2.setText("Artist ID: ");
            label3.setText("Name: ");
            label4.setText("Release Date: ");

            textField1.setEnabled(true);
            textField2.setEnabled(true);
            textField3.setEnabled(true);
            textField4.setEnabled(true);
        }
    }

    public void updateRecord(int choice, String ID) {
        int Id = Integer.parseInt(ID);
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

                sqlQuery = "UPDATE songs " +
                        "SET album_id  = " + "'" + album + "'," +
                        "genre_id  = " + "'" + genre + "'," +
                        "artist_song_id =  " + "'" + artist + "'," +
                        "name =  " + "'" + name + "'," +
                        "release_date  = " + "'" + releaseDate + "'," +
                        "lenght  = " + "'" + length + "'," +
                        "plays  = " + "'" + plays + "'" +
                        "WHERE id =" + "'" + Id + "'";

                PreparedStatement songStatement = connection.prepareStatement(sqlQuery);

                int resultSet = songStatement.executeUpdate();

            } else if (choice == 2) {

                String name = textField1.getText();
                String country = textField2.getText();

                sqlQuery = "UPDATE artists " +
                        "SET name = " + "'" + name + "'," +
                        "country = " + "'" + country + "'" +
                        "WHERE id =" + "'" + Id + "'";

                PreparedStatement artistStatement = connection.prepareStatement(sqlQuery);


                int resultSet = artistStatement.executeUpdate();

            } else if (choice == 3) {

                String genre = textField1.getText();
                String artist = textField2.getText();
                String name = textField3.getText();
                String releaseDate = textField4.getText();

                sqlQuery = "UPDATE albums " +
                        "SET genre_id  = " + "'" + genre + "'," +
                        "artist_song_id  = " + "'" + artist + "'," +
                        "name  = " + "'" + name + "'," +
                        "release_date  = " + "'" + releaseDate + "'" +
                        "WHERE id =" + "'" + Id + "'";

                PreparedStatement albumStatement = connection.prepareStatement(sqlQuery);

                int resultSet = albumStatement.executeUpdate();

            }
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
