package parcelpoint;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParcelService {
    private List<Locker> lockers = new ArrayList<>();
    private List<Parcel> parcels = new ArrayList<>();

    public ParcelService() {
        addLocker(1, 1);
        addLocker(2, 2);
        addLocker(3, 3);
    }

    private void addLocker(int number, int size) {
        if (number <= 0) {
            throw new IllegalArgumentException("Locker number must be positive.");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Locker size must be positive.");
        }

        for (Locker locker : lockers) {
            if (locker.getNumber() == number) {
                throw new IllegalArgumentException(
                        "Duplicate locker number: " + number);
            }
        }

        lockers.add(new Locker(number, size));
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    public Locker storeParcel(Parcel parcel) {
        for (Locker locker : lockers) {
            if (locker.isFree() && locker.getSize() >= parcel.getSize()) {
                locker.setParcel(parcel);
                parcel.setStatus("STORED");
                parcels.add(parcel);
                System.out.println("SMS to " + parcel.getStudentId() + ": parcel "
                        + parcel.getCode() + " is in locker " + locker.getNumber());
                saveToFile();
                return locker;
            }
        }
        return null;
    }

    public double pickUp(String code, int days) {
        for (Locker locker : lockers) {
            Parcel parcel = locker.getParcel();
            if (parcel != null && parcel.getCode().equals(code)) {
                locker.setParcel(null);
                parcel.setStatus("PICKED_UP");
                saveToFile();
                return fee(parcel, days);
            }
        }
        return 0;
    }

    public double fee(Parcel parcel, int days) {
        if (days <= 3) {
            return 0;
        }
        if (parcel.getSize() == 1) {
            return (days - 3) * 10.0;
        } else if (parcel.getSize() == 2) {
            return (days - 3) * 15.0;
        }
        return (days - 3) * 20.0;
    }

    public void printParcels(boolean forStaff) {
        for (Parcel parcel : parcels) {
            if (forStaff) {
                System.out.println(parcel.getCode() + " " + parcel.getStudentId() + " " + parcel.getStatus());
            } else if (parcel.status.equals("STORED")) {
                System.out.println(parcel.getCode() + " is waiting");
            }
        }
    }

    private void saveToFile() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("parcels.csv");
            for (Parcel parcel : parcels) {
                writer.write(parcel.getCode() + "," + parcel.status + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (writer != null) {
                try {
                    writer.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public long countFreeLockers() {
        return lockers.stream()
                .filter(Locker::isFree)
                .count();
    }
}
