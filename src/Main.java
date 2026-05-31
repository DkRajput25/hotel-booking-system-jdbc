import java.sql.*;
//import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/hotal_db";
    private static final String username = "root";
    private static final String password = "root";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        while (true) {

            System.out.println();
            System.out.println("HOTEL MANAGEMENT SYSTEM");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Reserve a Room");
            System.out.println("2. View Reserved Rooms");
            System.out.println("3. Get Room Number");
            System.out.println("4. Update Reservation");
            System.out.println("5. Delete Reservation");
            System.out.println("0. Exit");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    reserveRoom(connection, scanner);
                    break;
                case 2:
                    viewReservation(connection);
                    break;
                case 3:
                    getRoomNumber(connection, scanner);
                    break;
                case 4:
                    updateReservation(connection, scanner);
                    break;
                case 5:
                    deleteReservation(connection, scanner);

                    break;
                case 0:
                exit();
                    scanner.close();
                    return;

                default:
                    System.out.println("Wrong choice");
            }

        }
    }

    private static void reserveRoom(Connection connection, Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Enter Guest name: ");
            String guestName = scanner.nextLine();

            System.out.println("Enter Room number: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter contact Number: ");
            String contactNumber = scanner.next();

            String query = "INSERT INTO RESERVATION (guest_name, room_number, contact_number)" +
                    " VALUES ('" + guestName + "' ," + roomNumber + ", '" + contactNumber + "')";
            try (Statement statement = connection.createStatement()) {

                int rowaffected = statement.executeUpdate(query);
                if (rowaffected > 0) {
                    System.out.println("Reservation Successfully" + rowaffected);
                } else {
                    System.out.println("Reservation Failed" + rowaffected);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void viewReservation(Connection connection) {
        String query = "SELECT * FROM RESERVATION";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                String guestName = resultSet.getString("guest_name");
                int roomNumber = resultSet.getInt("room_number");
                String contactNumber = resultSet.getString("contact_number");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                System.out.println("=================================================");
                System.out.println("Reservation_Id: " + reservationId);
                System.out.println("Gust Name: " + guestName);
                System.out.println("Room Number: " + roomNumber);
                System.out.println("Contact Number: " + contactNumber);
                System.out.println("Reservation Date: " + reservationDate);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
//
    private static void getRoomNumber(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Reservation Id: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Guest name: ");

            String guestName = scanner.nextLine();

            String query = "SELECT room_number FROM RESERVATION" +
                    " WHERE reservation_id = " + reservationId + " " +
                    "AND guest_name='" + guestName + "'";

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    int roomNumber = resultSet.getInt("room_number");
                    System.out.println("Room Number for Reservation Id: " + reservationId + "and Guest" + guestName + "is : " + roomNumber);
                } else {
                    System.out.println("Reservation Not found for Reservation Id: " + reservationId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateReservation(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Reservation Id to update : ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();

            if (!reservationExist(connection, reservationId)) {
                System.out.println("Reservation not found for Reservation Id: " + reservationId);
                return;
            }

            System.out.println("Enter new Guest name: ");
            String newguestName = scanner.nextLine();

            System.out.println("Enter new Room number: ");
            int newroomNumber = scanner.nextInt();
//            scanner.nextLine();
            System.out.println("Enter new Contact Number: ");
            String newcontactNumber = scanner.next();


            String query = "update reservation set guest_name = '" + newguestName + "'," +
                    "room_number = " + newroomNumber + "," +
                    "contact_number = '" + newcontactNumber + "' " +
                    "where reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement()) {

                int rowaffected = statement.executeUpdate(query);
                if (rowaffected > 0) {
                    System.out.println("Reservation update Successfully" + rowaffected);
                } else {
                    System.out.println("Reservation update Failed" + rowaffected);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean reservationExist(Connection connection, int reservationId) {
        String query = "SELECT reservation_id FROM RESERVATION WHERE reservation_id = " + reservationId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            return (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void deleteReservation(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Reservation Id to delete : ");
            int reservationId = scanner.nextInt();
            if (!reservationExist(connection, reservationId)) {
                System.out.println("Reservation not found for Reservation Id: " + reservationId);
                return;
            }
            String query = "delete from RESERVATION where reservation_id = " + reservationId;
            try (Statement statement = connection.createStatement()) {

                int rowaffected = statement.executeUpdate(query);
                if (rowaffected > 0) {
                    System.out.println("Reservation Delete Successfully" + rowaffected);
                } else {
                    System.out.println("Reservation Delete Failed" + rowaffected);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(500);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou for using Hotal Reservation System!!!");

    }
    //\?.*
}
