package model.dao;

import model.bean.Lecturer;
import model.bean.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LecturerDAO {

	public boolean addLecturer(Lecturer lecturer) throws SQLException, ClassNotFoundException {
	    String sql = "INSERT INTO Lecturer (Name, Email, AccountID) VALUES (?, ?, ?)";
	    try (Connection connection = ConnectDatabase.getMySQLConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, lecturer.getName());
	        statement.setString(2, lecturer.getEmail());
	        statement.setInt(3, lecturer.getAccount().getAccountID());

	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0; // Trả về true nếu thêm thành công
	    }
	}
	public boolean isAccountAssigned(int accountID) throws SQLException, ClassNotFoundException {
	    String sql = "SELECT COUNT(*) FROM Lecturer WHERE AccountID = ?";
	    try (Connection connection = ConnectDatabase.getMySQLConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, accountID);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                return resultSet.getInt(1) > 0; // True nếu accountID đã tồn tại
	            }
	        }
	    }
	    return false; // False nếu accountID chưa tồn tại
	}
	 public List<Account> getUnusedAccounts() throws SQLException, ClassNotFoundException {
	        List<Account> accountList = new ArrayList<>();
	        String sql = "SELECT * FROM Account WHERE AccountID NOT IN (SELECT AccountID FROM Lecturer)";

	        try (Connection connection = ConnectDatabase.getMySQLConnection();
	             PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                Account account = new Account();
	                account.setAccountID(resultSet.getInt("AccountID"));
	                account.setUsername(resultSet.getString("Username"));
	                accountList.add(account);
	            }
	        }
	        return accountList;
	    }
    // Sửa giảng viên
	public boolean updateLecturer(Lecturer lecturer) throws SQLException, ClassNotFoundException {
	    if (lecturer == null || lecturer.getAccount() == null) {
	        throw new IllegalArgumentException("Lecturer or associated account is null.");
	    }
	    if (lecturer.getName() == null || lecturer.getName().trim().isEmpty()) {
	        throw new IllegalArgumentException("Lecturer name is required.");
	    }
	    if (lecturer.getEmail() == null || lecturer.getEmail().trim().isEmpty()) {
	        throw new IllegalArgumentException("Lecturer email is required.");
	    }
	    if (lecturer.getLecturerID() <= 0 || lecturer.getAccount().getAccountID() <= 0) {
	        throw new IllegalArgumentException("Invalid LecturerID or AccountID.");
	    }

	    String sql = "UPDATE Lecturer SET Name = ?, Email = ?, AccountID = ? WHERE LecturerID = ?";
	    try (Connection connection = ConnectDatabase.getMySQLConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        System.out.println("Executing SQL: " + sql);
	        System.out.println("Parameters: Name=" + lecturer.getName() +
	                           ", Email=" + lecturer.getEmail() +
	                           ", AccountID=" + lecturer.getAccount().getAccountID() +
	                           ", LecturerID=" + lecturer.getLecturerID());

	        statement.setString(1, lecturer.getName());
	        statement.setString(2, lecturer.getEmail());
	        statement.setInt(3, lecturer.getAccount().getAccountID());
	        statement.setInt(4, lecturer.getLecturerID());

	        int rowsUpdated = statement.executeUpdate();
	        System.out.println("Rows updated: " + rowsUpdated);
	        return rowsUpdated > 0; // Trả về true nếu cập nhật thành công
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error while updating lecturer: " + e.getMessage(), e);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        throw new ClassNotFoundException("Database connection class not found: " + e.getMessage(), e);
	    }
	}


    // Xóa giảng viên
    public boolean deleteLecturer(int lecturerID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Lecturer WHERE LecturerID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, lecturerID);

            return statement.executeUpdate() > 0; // Trả về true nếu xóa thành công
        }
    }

    // Lấy danh sách tất cả giảng viên
    public ArrayList<Lecturer> getAllLecturers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT L.LecturerID, L.Name, L.Email, A.AccountID, A.Username, A.Role, A.IsActive " +
                     "FROM Lecturer L " +
                     "JOIN Account A ON L.AccountID = A.AccountID";
        ArrayList<Lecturer> lecturers = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getMySQLConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("AccountID"),
                        resultSet.getString("Username"),
                        null,
                        resultSet.getString("Role"),
                        resultSet.getBoolean("IsActive")
                );

                Lecturer lecturer = new Lecturer(
                        resultSet.getInt("LecturerID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        account
                );

                lecturers.add(lecturer);
            }
        }
        return lecturers;
    }

    // Lấy thông tin giảng viên theo ID
    public Lecturer getLecturerById(int lecturerID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT L.LecturerID, L.Name, L.Email, A.AccountID, A.Username, A.Role, A.IsActive " +
                     "FROM Lecturer L " +
                     "JOIN Account A ON L.AccountID = A.AccountID WHERE L.LecturerID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, lecturerID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account(
                            resultSet.getInt("AccountID"),
                            resultSet.getString("Username"),
                            null,
                            resultSet.getString("Role"),
                            resultSet.getBoolean("IsActive")
                    );

                    return new Lecturer(
                            resultSet.getInt("LecturerID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Email"),
                            account
                    );
                }
            }
        }
        return null; // Không tìm thấy giảng viên
    }

        // Phương thức lấy thông tin giảng viên theo AccountID
        public Lecturer getLecturerByAccountID(int accountID) throws SQLException, ClassNotFoundException {
            String sql = "SELECT * FROM Lecturer WHERE AccountID = ?";
            try (Connection connection = ConnectDatabase.getMySQLConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, accountID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Lecturer lecturer = new Lecturer();
                        lecturer.setLecturerID(resultSet.getInt("LecturerID"));
                        lecturer.setName(resultSet.getString("Name"));
                        // Cập nhật các trường khác nếu cần
                        return lecturer;
                    }
                }
            }
            return null; // Nếu không tìm thấy giảng viên
        }
        public boolean isLecturerAvailable(int lecturerID, String dayOfWeek, String startTime, String endTime) throws SQLException, ClassNotFoundException {
            String sql = "SELECT COUNT(*) FROM Schedule WHERE LecturerID = ? AND DayOfWeek = ? AND " +
                         "(StartTime < ? AND EndTime > ?)";
            try (Connection connection = ConnectDatabase.getMySQLConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, lecturerID);
                statement.setString(2, dayOfWeek);
                statement.setString(3, endTime);
                statement.setString(4, startTime);
                
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0; // Không có lịch trùng
                }
            }
            return false;
        }

      

    }


