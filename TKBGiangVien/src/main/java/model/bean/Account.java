package model.bean;

public class Account {
    // Các thuộc tính của lớp Account
    private int accountID;   // Mã tài khoản (Primary Key)
    private String username; // Tên đăng nhập
    private String password; // Mật khẩu (nên mã hóa)
    private String role;     // Vai trò (Lecturer, Admin, etc.)
    private boolean isActive; // Trạng thái kích hoạt

    // Constructor không tham số
    public Account() {
    }

    // Constructor đầy đủ tham số
    public Account(int accountID, String username, String password, String role, boolean isActive) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }
    public Account(int accountID, String username) {
        this.accountID = accountID;
        this.username = username;
    }

    // Getter và Setter cho từng thuộc tính
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

        // Getter cho isActive
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }


    }
