package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDatabase {
	
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        // Cấu hình cơ sở dữ liệu
        String dbURL = "jdbc:mysql://localhost:3306/tkbgv";
        String username = "root";
        String password = "123456";

        // Tải driver MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Kết nối đến cơ sở dữ liệu
        Connection conn = DriverManager.getConnection(dbURL, username, password);

        // Kiểm tra kết nối
        if (conn != null) {
            System.out.println("Kết nối thành công!");
        }
        return conn;
    }

    // Hàm main để kiểm tra kết nối
    public static void main(String[] args) {
        try {
            Connection connection = getMySQLConnection();
            if (connection != null) {
                System.out.println("Kết nối DB thành công!");
                connection.close(); // Đóng kết nối sau khi sử dụng
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Driver không tìm thấy!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu!");
            e.printStackTrace();
        }
    }
}