package parcelpoint;

public class Locker {
    private int number;
    private LockerSize size;    
    private Parcel parcel;

    public Locker(int number, LockerSize size) {
        this.number = number;
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public LockerSize getSize() {
        return size;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public boolean isFree() {
        return parcel == null;
    }
}
