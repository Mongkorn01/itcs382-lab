package bikeshare;

import java.util.ArrayDeque;
import java.util.Queue;

public class Station {
    private final StationName name;
    private final Queue<Bike> availableBikes = new ArrayDeque<>();

    public Station(StationName name) {
        this.name = name;
    }

    public StationName getName() {
        return name;
    }

    public void addBike(Bike bike) {
        availableBikes.add(bike);
    }

    public Bike rentBike() {
        return availableBikes.poll();
    }

    public int getAvailableCount() {
        return availableBikes.size();
    }
}
