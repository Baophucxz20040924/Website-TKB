package model.bean;

public class Class {
    // Các thuộc tính của lớp Class
    private int classID;          // Mã lớp học (Primary Key)
    private String className;     // Tên lớp học
    private int numberOfStudents; // Số lượng sinh viên

    // Constructor không tham số
    public Class() {
    }

    // Constructor đầy đủ tham số
    public Class(int classID, String className, int numberOfStudents) {
        this.classID = classID;
        this.className = className;
        this.numberOfStudents = numberOfStudents;
    }

    // Getter và Setter cho từng thuộc tính
    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

}
