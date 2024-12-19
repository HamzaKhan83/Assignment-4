package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PunishmentSystem {
    public void addCrime(String crimeName, String punishmentDescription) {
        String query = "INSERT INTO Punishments (crime_name, punishment_description) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, crimeName);
            preparedStatement.setString(2, punishmentDescription);
            preparedStatement.executeUpdate();
            System.out.println("Crime added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllCrimes() {
        String query = "SELECT * FROM Punishments";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Crime List:");
            while (resultSet.next()) {
                System.out.println("Crime ID: " + resultSet.getInt("crime_id"));
                System.out.println("Crime Name: " + resultSet.getString("crime_name"));
                System.out.println("Punishment: " + resultSet.getString("punishment_description"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

