package bikeshare;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeShare {
    public static final int STUDENT = 1, STAFF = 2;
    public static final List<Bike> bikes = new ArrayList<>();
    public static final String[] stations = {"Library", "Canteen", "Dorm"};
    public static final Path FILE = Path.of("rides.csv");

    static {
        for (String station : stations) {
            for (int i = 1; i <= 2; i++) {
                bikes.add(new Bike(station.charAt(0) + "" + i, station));
            }
        }
    }

    public static class Bike {
        public String id, station, memberId = "";
        public int memberType;
        public boolean rented;

        public Bike(String id, String station) {
            this.id = id;
            this.station = station;
        }
    }

    public static void run(Scanner in) {
        System.out.println("BikeShare: rent, return, quit");
        while (true) {
            System.out.print("> ");
            String command = in.nextLine().trim().toLowerCase();
            if (command.equals("quit")) break;
            if (command.equals("rent")) processRide(in, false);
            else if (command.equals("return")) processRide(in, true);
            else System.out.println("Unknown command.");
        }
    }

    // The flag makes this method handle either a rental or a return.
    public static void processRide(Scanner in, boolean returning) {
        try {
            if (!returning) {
                System.out.print("Member ID: ");
                String memberId = in.nextLine().trim();
                System.out.print("Type (1=student, 2=staff): ");
                int type = Integer.parseInt(in.nextLine().trim());
                if (memberId.isEmpty() || (type != STUDENT && type != STAFF)) {
                    System.out.println("Invalid member details.");
                    return;
                }

                System.out.print("Station (Library/Canteen/Dorm): ");
                String station = findStation(in.nextLine().trim());
                if (station == null) {
                    System.out.println("Invalid station.");
                    return;
                }

                for (Bike bike : bikes) {
                    if (bike.station.equals(station) && !bike.rented) {
                        bike.rented = true;
                        bike.memberId = memberId;
                        bike.memberType = type;
                        System.out.println("Bike " + bike.id + " rented successfully.");
                        return;
                    }
                }
                System.out.println("No bikes available at " + station + ".");
            } else {
                System.out.print("Bike ID: ");
                String id = in.nextLine().trim();
                Bike bike = null;
                for (Bike b : bikes) {
                    if (b.id.equalsIgnoreCase(id) && b.rented) {
                        bike = b;
                        break;
                    }
                }
                if (bike == null) {
                    System.out.println("Rented bike not found.");
                    return;
                }

                System.out.print("Return station (Library/Canteen/Dorm): ");
                String station = findStation(in.nextLine().trim());
                if (station == null) {
                    System.out.println("Invalid station.");
                    return;
                }
                System.out.print("Ride time in minutes: ");
                int minutes = Integer.parseInt(in.nextLine().trim());
                if (minutes <= 0) {
                    System.out.println("Ride time must be positive.");
                    return;
                }

                int fee = minutes * (bike.memberType == STUDENT ? 2 : 3);
                System.out.println("\n--- Receipt ---");
                System.out.println("Member: " + bike.memberId);
                System.out.println("Bike: " + bike.id);
                System.out.println("Minutes: " + minutes);
                System.out.println("Fee: " + fee + " baht");
                System.out.println("Returned at: " + station);

                saveRide(bike, minutes, fee, station);
                bike.station = station;
                bike.rented = false;
                bike.memberId = "";
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static String findStation(String name) {
        for (String station : stations) {
            if (station.equalsIgnoreCase(name)) return station;
        }
        return null;
    }

    private static void saveRide(Bike bike, int minutes, int fee, String station) {
        try {
            boolean header = !Files.exists(FILE) || Files.size(FILE) == 0;
            try (BufferedWriter out = Files.newBufferedWriter(FILE,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                if (header) out.write("member_id,member_type,bike_id,minutes,fee,return_station\n");
                out.write(bike.memberId + "," +
                        (bike.memberType == STUDENT ? "student" : "staff") + "," +
                        bike.id + "," + minutes + "," + fee + "," + station + "\n");
            }
        } catch (IOException e) {
            System.out.println("Could not save ride: " + e.getMessage());
        }
    }
}