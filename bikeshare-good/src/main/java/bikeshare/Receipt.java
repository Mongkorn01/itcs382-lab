package bikeshare;

public class Receipt {
    public void print(Ride ride) {
        System.out.println("\n--- BikeShare Receipt ---");
        System.out.println("Member: " + ride.getMemberId());
        System.out.println("Bike: " + ride.getBike().getId());
        System.out.println("From: " + ride.getStartStation());
        System.out.println("Returned to: " + ride.getReturnStation());
        System.out.println("Minutes: " + ride.getMinutes());
        System.out.println("Fee: " + ride.getFee() + " baht");
        System.out.println("-------------------------\n");
    }
}
