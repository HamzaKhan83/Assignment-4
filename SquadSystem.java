package test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SquadSystem {

    public void createSquad(String squadName) {
        String query = "INSERT INTO Squads (squad_name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, squadName);
            preparedStatement.executeUpdate();
            System.out.println("Squad created successfully: " + squadName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignOfficerToSquad(int officerId, int squadId) {
        String query = "INSERT INTO SquadAssignments (officer_id, squad_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, officerId);
            preparedStatement.setInt(2, squadId);
            preparedStatement.executeUpdate();
            System.out.println("Officer assigned to squad successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void requestSquadJoin(int officerId, String squadName) {
        String query = "SELECT squad_id FROM Squads WHERE squad_name = ?";
        int squadId = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, squadName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                squadId = resultSet.getInt("squad_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (squadId != -1) {
            System.out.println("Officer " + officerId + " requested to join " + squadName);
            // Here we send a request, and the executor can approve/reject or assign to a different squad
        } else {
            System.out.println("Squad " + squadName + " does not exist.");
        }
    }

    public void acceptOfficerToSquad(int officerId, int squadId) {
        String query = "INSERT INTO SquadAssignments (officer_id, squad_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, officerId);
            preparedStatement.setInt(2, squadId);
            preparedStatement.executeUpdate();
            System.out.println("Officer " + officerId + " accepted into squad ID " + squadId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignOfficerToAnotherSquad(int officerId, int newSquadId) {
        String query = "UPDATE SquadAssignments SET squad_id = ? WHERE officer_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, newSquadId);
            preparedStatement.setInt(2, officerId);
            preparedStatement.executeUpdate();
            System.out.println("Officer " + officerId + " assigned to new squad ID " + newSquadId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rejectOfficerRequest(int officerId) {
        System.out.println("Officer " + officerId + " request to join squad rejected.");
    }
}

