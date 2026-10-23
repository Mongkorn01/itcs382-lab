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
    
    public static LockerSize fromCode(String code) {
        return switch (code) {
            case "1" -> SMALL;
            case "2" -> MEDIUM;
            case "3" -> LARGE;
            default -> throw new IllegalArgumentException(
                "Invalid size. Use 1 (small), 2 (medium), or 3 (large)."
            );
        };
    }
}
