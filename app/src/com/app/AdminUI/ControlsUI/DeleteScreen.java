package com.app.AdminUI.ControlsUI;

import com.app.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteScreen extends JFrame {

    private JPanel deletePanel;
    private JTextField IDTextField;
    private JButton deleteButton;

    public DeleteScreen(int choice) {
        add(deletePanel);
        setSize(400, 200);
        setTitle("Music App / Admin Delete Data");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDTextField.getText();

                deleteRecord(choice, ID);
            }
        });
    }

    public void deleteRecord(int choice, String ID) {
        try {
            Connection connection = DBConnection.getConnection();
            String sqlQuery = null;

            if (choice == 1) {
                sqlQuery = "DELETE  FROM songs WHERE id = '" + ID + "'";

                PreparedStatement songStatement = connection.prepareStatement(sqlQuery);
                int resultSet = songStatement.executeUpdate();

                if (resultSet != 0) {
                    songStatement.execute();
                }

            } else if (choice == 2) {
                sqlQuery = "DELETE  FROM artists WHERE id=  '" + ID + "'";

                PreparedStatement artistStatement = connection.prepareStatement(sqlQuery);
                int resultSet = artistStatement.executeUpdate();

                if (resultSet != 0) {
                    artistStatement.execute();
                }

            } else if (choice == 3) {
                sqlQuery = "DELETE  FROM albums WHERE id= '" + ID + "'";

                PreparedStatement albumStatement = connection.prepareStatement(sqlQuery);
                int resultSet = albumStatement.executeUpdate();

                if (resultSet != 0) {
                    albumStatement.execute();
                }
            }
            connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
