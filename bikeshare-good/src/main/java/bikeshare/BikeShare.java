package bikeshare;

import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BikeShare {
    private final Map<StationName, Station> stations =
            new EnumMap<>(StationName.class);

    private final Map<String, Ride> activeRides = new HashMap<>();
    private final RideRepository repository;

    public BikeShare(RideRepository repository) {
        this.repository = repository;

        for (StationName name : StationName.values()) {
            Station station = new Station(name);
            station.addBike(new Bike(name.name() + "-1"));
            station.addBike(new Bike(name.name() + "-2"));
            stations.put(name, station);
        }
    }

    public Ride rent(String memberId, MemberType type,
                     StationName stationName) {
        if (memberId == null || memberId.isBlank()) {
            throw new IllegalArgumentException("Member ID is required.");
        }

        Station station = stations.get(stationName);
        Bike bike = station.rentBike();

        if (bike == null) {
            throw new IllegalStateException(
                    stationName + " has no available bikes.");
        }

        Ride ride = new Ride(memberId, type, bike, stationName);
        activeRides.put(bike.getId(), ride);
        return ride;
    }

    public Ride returnBike(String bikeId, StationName stationName,
                           int minutes) throws IOException {
        Ride ride = activeRides.get(bikeId);

        if (ride == null) {
            throw new IllegalArgumentException(
                    "No active ride found for bike " + bikeId + ".");
        }

        ride.complete(minutes, stationName);
        stations.get(stationName).addBike(ride.getBike());
        activeRides.remove(bikeId);
        repository.save(ride);

        return ride;
    }

    public void printAvailability() {
        for (Station station : stations.values()) {
            System.out.println(station.getName() + ": "
                    + station.getAvailableCount() + " bike(s)");
        }
    }
}
