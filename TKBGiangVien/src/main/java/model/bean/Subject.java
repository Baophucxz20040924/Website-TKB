package model.bean;

public class Subject {
    private int subjectID;      // Mã môn học
    private String subjectName; // Tên môn học
    private int credits;        // Số tín chỉ

    // Constructor không tham số
    public Subject() {
    }

    // Constructor đầy đủ tham số
    public Subject(int subjectID, String subjectName, int credits) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    // Getter và Setter cho subjectID
    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    // Getter và Setter cho subjectName
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    // Getter và Setter cho credits
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
