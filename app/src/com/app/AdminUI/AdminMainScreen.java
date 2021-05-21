package com.app.AdminUI;

import com.app.AdminUI.ControlsUI.AddScreen;
import com.app.AdminUI.ControlsUI.DeleteScreen;
import com.app.AdminUI.ControlsUI.UpdateScreen;
import com.app.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMainScreen extends JFrame {
    public int choice = 0;
    Object[] songColumns = {"id", "album_id", "genre_id", "artist_song_id", "name", "release_date",
            "length", "plays", "created_at", "updated_at", "deleted_at"};
    Object[] songRows = new Object[11];
    Object[] artistColumns = {"id", "name", "country", "created_at", "updated_at", "deleted_at"};
    Object[] artistRows = new Object[6];
    Object[] albumColumns = {"id", "genre_id", "artist_song_id", "name", "release_date", "created_at", "updated_at", "deleted_at"};
    Object[] albumRows = new Object[8];
    private JPanel adminMainScreenPanel;
    private JTable adminTable;
    private JButton songsButton;
    private JButton artistsButton;
    private JButton albumsButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public AdminMainScreen() {
        add(adminMainScreenPanel);
        setSize(1200, 800);
        setTitle("Music App / Admin Main");

        songsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = 1;
                printTable(choice);
            }
        });

        artistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = 2;
                printTable(choice);
            }
        });

        albumsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = 3;
                printTable(choice);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddScreen addScreen = new AddScreen(choice);
                addScreen.setVisible(true);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateScreen updateScreen = new UpdateScreen(choice);
                updateScreen.setVisible(true);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteScreen deleteScreen = new DeleteScreen(choice);
                deleteScreen.setVisible(true);
            }
        });
    }

    public void printTable(int choice) {
        try {
            Connection connection = DBConnection.getConnection();
            String sqlQuery = null;

            adminTable.setFillsViewportHeight(true);

            if (choice == 1) {
                sqlQuery = "SELECT * FROM songs";

                PreparedStatement songStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = songStatement.executeQuery();

                DefaultTableModel songModel = new DefaultTableModel();
                songModel.setColumnIdentifiers(songColumns);

                while (resultSet.next()) {
                    songRows[0] = resultSet.getString("id");
                    songRows[1] = resultSet.getString("album_id");
                    songRows[2] = resultSet.getString("genre_id");
                    songRows[3] = resultSet.getString("artist_id");
                    songRows[4] = resultSet.getString("name");
                    songRows[5] = resultSet.getString("release_date");
                    songRows[6] = resultSet.getString("lenght");
                    songRows[7] = resultSet.getString("plays");
                    songRows[8] = resultSet.getString("created_at");
                    songRows[9] = resultSet.getString("updated_at");
                    songRows[10] = resultSet.getString("deleted_at");

                    songModel.addRow(songRows);
                    adminTable.setModel(songModel);
                }


            } else if (choice == 2) {
                sqlQuery = "SELECT * FROM artists";

                PreparedStatement artistStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = artistStatement.executeQuery();

                DefaultTableModel artistModel = new DefaultTableModel();
                artistModel.setColumnIdentifiers(artistColumns);

                while (resultSet.next()) {
                    artistRows[0] = resultSet.getString("id");
                    artistRows[1] = resultSet.getString("name");
                    artistRows[2] = resultSet.getString("country");
                    artistRows[3] = resultSet.getString("created_at");
                    artistRows[4] = resultSet.getString("updated_at");
                    artistRows[5] = resultSet.getString("deleted_at");

                    artistModel.addRow(artistRows);
                    adminTable.setModel(artistModel);
                }

            } else if (choice == 3) {
                sqlQuery = "SELECT * FROM albums";

                PreparedStatement albumStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = albumStatement.executeQuery();

                DefaultTableModel albumModel = new DefaultTableModel();
                albumModel.setColumnIdentifiers(albumColumns);

                while (resultSet.next()) {
                    albumRows[0] = resultSet.getString("id");
                    albumRows[1] = resultSet.getString("genre_id");
                    albumRows[2] = resultSet.getString("artist_id");
                    albumRows[3] = resultSet.getString("name");
                    albumRows[4] = resultSet.getString("release_date");
                    albumRows[5] = resultSet.getString("created_at");
                    albumRows[6] = resultSet.getString("updated_at");
                    albumRows[7] = resultSet.getString("deleted_at");

                    albumModel.addRow(albumRows);
                    adminTable.setModel(albumModel);
                }

            }
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
