package test;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //user login
        System.out.println("Welcome to the Crime Management System");
        System.out.println("Please select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Report a crime (General Public)");
        System.out.print("Enter choice (1/2/3): ");
        int choice = scanner.nextInt();

        scanner.nextLine();

        String username, password, role;

        if (choice == 1) {
            // User login
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            System.out.print("Enter role (Executor/Officer): ");
            role = scanner.nextLine();

            UserAuthentication userAuth = new UserAuthentication();
            boolean isLoggedIn = userAuth.login(username, password, role);

            if (isLoggedIn) {
                // Proceed based on role
                if (role.equalsIgnoreCase("Executor")) {
                    executorMenu(scanner);
                } else if (role.equalsIgnoreCase("Officer")) {
                    officerMenu(scanner, username);
                }
            }
        } else if (choice == 2) {
            // User registration
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            System.out.print("Enter role (Executor/Officer): ");
            role = scanner.nextLine();

            UserAuthentication userAuth = new UserAuthentication();
            userAuth.registerUser(username, password, role);
        } else if (choice == 3) {

            reportCrime(scanner);
        } else {
            System.out.println("Invalid option. Exiting...");
        }
    }

    private static void executorMenu(Scanner scanner) {
        ExecutorFunctions executorFunctions = new ExecutorFunctions();
        ReportSystem reportSystem = new ReportSystem();

        while (true) {
            System.out.println("\nExecutor Menu:");
            System.out.println("1. Create a new report");
            System.out.println("2. View all reports");
            System.out.println("3. Assign punishment to criminal");
            System.out.println("4. Assign case to officer");
            System.out.println("5. Revoke case from officer");
            System.out.println("6. Assign bounty punishment to criminal");
            System.out.println("7. Create squad");
            System.out.println("8. Assign officer to squad");
            System.out.println("9. Request officer to join squad");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.print("Enter reporter name: ");
                String reporterName = scanner.nextLine();
                System.out.print("Enter reporter CNIC: ");
                String reporterCnic = scanner.nextLine();
                System.out.print("Enter crime details: ");
                String crimeDetails = scanner.nextLine();
                System.out.print("Enter possible suspects (comma-separated): ");
                String possibleSuspects = scanner.nextLine();
                reportSystem.createReport(reporterName, reporterCnic, crimeDetails, possibleSuspects);
            } else if (choice == 2) {

                reportSystem.viewAllReports();
            } else if (choice == 3) {

                System.out.print("Enter criminal ID: ");
                int criminalId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter punishment: ");
                String punishment = scanner.nextLine();
                executorFunctions.assignPunishment(criminalId, punishment);
            } else if (choice == 4) {

                System.out.print("Enter officer ID: ");
                int officerId = scanner.nextInt();
                System.out.print("Enter report ID: ");
                int reportId = scanner.nextInt();
                executorFunctions.assignCaseToOfficer(officerId, reportId);
            } else if (choice == 5) {
                System.out.print("Enter report ID: ");
                int reportId = scanner.nextInt();
                executorFunctions.revokeCaseFromOfficer(reportId);
            } else if (choice == 6) {

                System.out.print("Enter criminal ID: ");
                int criminalId = scanner.nextInt();
                System.out.print("Enter bounty amount: ");
                double bounty = scanner.nextDouble();
                executorFunctions.updateBountyPunishment(criminalId, bounty);
            } else if (choice == 7) {
                System.out.print("Enter squad name: ");
                String squadName = scanner.nextLine();
                executorFunctions.createSquad(squadName);
            } else if (choice == 8) {

                System.out.print("Enter officer ID: ");
                int officerId = scanner.nextInt();
                System.out.print("Enter squad ID: ");
                int squadId = scanner.nextInt();
                executorFunctions.assignOfficerToSquad(officerId, squadId);
            } else if (choice == 9) {

                System.out.print("Enter officer ID: ");
                int officerId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter squad name: ");
                String squadName = scanner.nextLine();
                executorFunctions.requestSquadJoin(officerId, squadName);
            } else if (choice == 10) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void officerMenu(Scanner scanner, String username) {
        OfficerFunctions officerFunctions = new OfficerFunctions();
        ReportSystem reportSystem = new ReportSystem();

        while (true) {
            System.out.println("\nOfficer Menu:");
            System.out.println("1. Create a new report");
            System.out.println("2. View assigned cases");
            System.out.println("3. Update case status");
            System.out.println("4. Request to join a squad");
            System.out.println("5. View current squad");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.print("Enter reporter name: ");
                String reporterName = scanner.nextLine();
                System.out.print("Enter reporter CNIC: ");
                String reporterCnic = scanner.nextLine();
                System.out.print("Enter crime details: ");
                String crimeDetails = scanner.nextLine();
                System.out.print("Enter possible suspects (comma-separated): ");
                String possibleSuspects = scanner.nextLine();
                reportSystem.createReport(reporterName, reporterCnic, crimeDetails, possibleSuspects);
            } else if (choice == 2) {
                officerFunctions.viewAssignedCases(username);
            } else if (choice == 3) {
                officerFunctions.updateCaseStatus(scanner, username);
            } else if (choice == 4) {
                officerFunctions.requestToJoinSquad(scanner, username);
            } else if (choice == 5) {
                officerFunctions.viewCurrentSquad(username);
            } else if (choice == 6) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void reportCrime(Scanner scanner) {

        ReportSystem reportSystem = new ReportSystem();
        System.out.print("Enter reporter name: ");
        String reporterName = scanner.nextLine();
        System.out.print("Enter reporter CNIC: ");
        String reporterCnic = scanner.nextLine();
        System.out.print("Enter crime details: ");
        String crimeDetails = scanner.nextLine();
        System.out.print("Enter possible suspects (comma-separated): ");
        String possibleSuspects = scanner.nextLine();
        reportSystem.createReport(reporterName, reporterCnic, crimeDetails, possibleSuspects);
    }
}
