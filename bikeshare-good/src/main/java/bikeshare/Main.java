package bikeshare;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BikeShare bikeShare = new BikeShare(
                new CsvRideRepository(Path.of("rides.csv")));
        Receipt receipt = new Receipt();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print(
                        "\nCommands: rent, return, status, quit\n> ");
                String command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("quit")) {
                    break;
                }

                try {
                    switch (command.toLowerCase()) {
                        case "rent" -> {
                            System.out.print("Member ID: ");
                            String memberId = scanner.nextLine();

                            System.out.print("Member type (student/staff): ");
                            MemberType type =
                                    MemberType.from(scanner.nextLine());

                            System.out.print("Station (Library/Canteen/Dorm): ");
                            StationName station =
                                    StationName.from(scanner.nextLine());

                            Ride ride = bikeShare.rent(
                                    memberId, type, station);

                            System.out.println("Bike rented: "
                                    + ride.getBike().getId());
                        }
                        case "return" -> {
                            System.out.print("Bike ID: ");
                            String bikeId = scanner.nextLine().trim();

                            System.out.print("Return station: ");
                            StationName station =
                                    StationName.from(scanner.nextLine());

                            System.out.print("Ride time in minutes: ");
                            int minutes = Integer.parseInt(
                                    scanner.nextLine().trim());

                            Ride ride = bikeShare.returnBike(
                                    bikeId, station, minutes);
                            receipt.print(ride);
                            System.out.println("Ride saved to rides.csv.");
                        }
                        case "status" -> bikeShare.printAvailability();
                        default -> System.out.println("Unknown command.");
                    }
                } catch (IllegalArgumentException
                         | IllegalStateException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Could not save ride: "
                            + e.getMessage());
                }
            }
        }

        System.out.println("BikeShare closed.");
    }
}
