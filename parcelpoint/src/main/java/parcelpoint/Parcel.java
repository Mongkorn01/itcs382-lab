package parcelpoint;

public class Parcel {
    private String code;
    private String studentId;
    private LockerSize size;    
    public String status;

    public Parcel(String code, String studentId, LockerSize size) {
        this.code = code;
        this.studentId = studentId;
        this.size = size;
        this.status = "NEW";
    }

    public String getCode() {
        return code;
    }

    public String getStudentId() {
        return studentId;
    }

    public LockerSize getSize() {
        return size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
