package model.dao;

import model.bean.Class;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {

    // Thêm lớp học
    public int insertClass(Class cls) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Class (ClassName, NumberOfStudents) VALUES (?, ?)";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cls.getClassName());
            statement.setInt(2, cls.getNumberOfStudents());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Trả về ClassID vừa được tạo
                    }
                }
            }
            return -1; // Không thêm được lớp học
        }
    }

    // Sửa lớp học
    public int updateClass(Class cls) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Class SET ClassName = ?, NumberOfStudents = ? WHERE ClassID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cls.getClassName());
            statement.setInt(2, cls.getNumberOfStudents());
            statement.setInt(3, cls.getClassID());
            return statement.executeUpdate(); // Trả về số hàng bị ảnh hưởng
        }
    }

    // Xóa lớp học
    public int deleteClass(int classID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Class WHERE ClassID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classID);
            return statement.executeUpdate(); // Trả về số hàng bị xóa
        }
    }

    // Lấy danh sách tất cả lớp học
    public List<Class> getAllClasses() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Class";
        List<Class> classes = new ArrayList<>();

        try (Connection connection = ConnectDatabase.getMySQLConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Class cls = new Class(
                    resultSet.getInt("ClassID"),
                    resultSet.getString("ClassName"),
                    resultSet.getInt("NumberOfStudents")
                );
                classes.add(cls);
            }
        }
        return classes;
    }

    // Lấy thông tin lớp học theo ID
    public Class getClassById(int classID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Class WHERE ClassID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Class(
                        resultSet.getInt("ClassID"),
                        resultSet.getString("ClassName"),
                        resultSet.getInt("NumberOfStudents")
                    );
                }
            }
        }
        return null; // Không tìm thấy lớp học
    }
}
