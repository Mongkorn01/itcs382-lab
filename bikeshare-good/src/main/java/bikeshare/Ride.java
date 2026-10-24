package bikeshare;

public class Ride {
    private final String memberId;
    private final MemberType memberType;
    private final Bike bike;
    private final StationName startStation;

    private StationName returnStation;
    private int minutes;
    private int fee;
    private boolean completed;

    public Ride(String memberId, MemberType memberType,
                Bike bike, StationName startStation) {
        this.memberId = memberId;
        this.memberType = memberType;
        this.bike = bike;
        this.startStation = startStation;
    }

    public void complete(int minutes, StationName returnStation) {
        if (completed || minutes <= 0) {
            throw new IllegalArgumentException(
                    "Ride must be active and minutes must be positive.");
        }

        this.minutes = minutes;
        this.returnStation = returnStation;
        this.fee = minutes * memberType.getFeePerMinute();
        this.completed = true;
    }

    public String getMemberId() {
        return memberId;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public Bike getBike() {
        return bike;
    }

    public StationName getStartStation() {
        return startStation;
    }

    public StationName getReturnStation() {
        return returnStation;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getFee() {
        return fee;
    }

    public boolean isCompleted() {
        return completed;
    }
}
