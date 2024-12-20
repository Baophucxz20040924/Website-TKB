package model.bean;

public class Lecturer {
    // Các thuộc tính của lớp Lecturer
    private int lecturerID; // Mã giảng viên
    private String name;    // Tên giảng viên
    private String email;   // Email giảng viên
    private Account account; // Đối tượng Account (quan hệ 1-1)

    // Constructor không tham số
    public Lecturer() {
    }

    // Constructor đầy đủ tham số
    public Lecturer(int lecturerID, String name, String email, Account account) {
        this.lecturerID = lecturerID;
        this.name = name;
        this.email = email;
        this.account = account;
    }

    // Getter và Setter
    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}

