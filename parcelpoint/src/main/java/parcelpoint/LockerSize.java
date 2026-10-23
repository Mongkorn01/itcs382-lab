package parcelpoint;

public enum LockerSize {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int rank;

    LockerSize(int rank) {
        this.rank = rank;
    }

    public boolean canFit(LockerSize parcelSize) {
        return this.rank >= parcelSize.rank;
    }
}
