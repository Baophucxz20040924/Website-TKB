package model.dao;

import model.bean.Account;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;

public class AccountDAO {

	public int insertAccount(Account account) throws SQLException, ClassNotFoundException {
	    String sql = "INSERT INTO Account (Username, Password, Role, IsActive) VALUES (?, ?, ?, ?)";
	    try (Connection connection = ConnectDatabase.getMySQLConnection();
	         PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        statement.setString(1, account.getUsername());
	        statement.setString(2, account.getPassword());
	        statement.setString(3, account.getRole());
	        statement.setBoolean(4, account.isActive());

	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            // Lấy AccountID vừa được tạo
	            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    return generatedKeys.getInt(1); // Trả về AccountID
	                }
	            }
	        }
	        return -1; // Không thêm được tài khoản
	    }
	}

    // Sửa tài khoản
    public int updateAccount(Account account) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Account SET Username = ?, Password = ?, Role = ?, IsActive = ? WHERE AccountID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getRole());
            statement.setBoolean(4, account.isActive());
            statement.setInt(5, account.getAccountID());
            return statement.executeUpdate();
        }
    }

    // Xóa tài khoản
    public int deleteAccount(int accountID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Account WHERE AccountID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountID);
            return statement.executeUpdate();
        }
    }
    public ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tkbgv.account";
        ArrayList<Account> accounts = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getMySQLConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Account account = new Account(
                    resultSet.getInt("AccountID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Role"),
                    resultSet.getBoolean("IsActive")
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách tài khoản: " + e.getMessage());
            throw e;
        }

        return accounts;
    }
    public Account findAccountByUsername(String username) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM account WHERE Username = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Account(
                        resultSet.getInt("AccountID"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Role"),
                        resultSet.getBoolean("IsActive")
                    );
                }
            }
        }
        return null; // Không tìm thấy tài k
    }}
