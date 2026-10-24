package bikeshare;

public enum StationName {
    LIBRARY("Library"),
    CANTEEN("Canteen"),
    DORM("Dorm");

    private final String displayName;

    StationName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static StationName from(String value) {
        for (StationName station : values()) {
            if (station.displayName.equalsIgnoreCase(value.trim())) {
                return station;
            }
        }
        throw new IllegalArgumentException("Unknown station.");
    }
}
