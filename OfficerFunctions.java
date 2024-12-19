package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OfficerFunctions {

    public void viewAssignedCases(String officerUsername) {
        String query = "SELECT * FROM Reports WHERE officer_id IN (SELECT user_id FROM Users WHERE username = ? AND role = 'Officer')";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, officerUsername);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Assigned Cases:");
            while (resultSet.next()) {
                int reportId = resultSet.getInt("report_id");
                String caseStatus = resultSet.getString("status");
                System.out.println("Report ID: " + reportId + " | Status: " + caseStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCaseStatus(Scanner scanner, String officerUsername) {
        System.out.print("Enter report ID to update: ");
        int reportId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new case status: ");
        String newStatus = scanner.nextLine();

        String query = "UPDATE Reports SET status = ? WHERE report_id = ? AND officer_id IN (SELECT officer_id FROM Officers WHERE username = ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, reportId);
            preparedStatement.setString(3, officerUsername);
            preparedStatement.executeUpdate();

            System.out.println("Case status updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void requestToJoinSquad(Scanner scanner, String officerUsername) {
        System.out.print("Enter the squad name to join: ");
        String squadName = scanner.nextLine();

        String query = "SELECT squad_id FROM Squads WHERE squad_name = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, squadName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int squadId = resultSet.getInt("squad_id");
                // Logic for requesting to join the squad
                System.out.println("Officer " + officerUsername + " requested to join squad " + squadName);
                // A request to join is sent to the Executor (can be handled separately)
            } else {
                System.out.println("Squad does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewCurrentSquad(String officerUsername) {
        String query = "SELECT squad_name FROM Squads WHERE squad_id IN (SELECT squad_id FROM SquadAssignments WHERE officer_id IN (SELECT officer_id FROM Officers WHERE username = ?))";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, officerUsername);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String squadName = resultSet.getString("squad_name");
                System.out.println("Officer " + officerUsername + " is part of squad: " + squadName);
            } else {
                System.out.println("Officer " + officerUsername + " is not part of any squad.");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

}
