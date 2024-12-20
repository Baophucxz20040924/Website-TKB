package model.dao;

import model.bean.Subject;
import model.dao.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    // Thêm môn học
	public int insertSubject(Subject subject) throws SQLException, ClassNotFoundException {
	    String sql = "INSERT INTO subject (subjectName, credits) VALUES (?, ?)";
	    try (Connection connection = ConnectDatabase.getMySQLConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, subject.getSubjectName());
	        statement.setInt(2, subject.getCredits());

	        return statement.executeUpdate(); // Trả về số hàng bị ảnh hưởng
	    }
	}

    // Cập nhật môn học
    public int updateSubject(Subject subject) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Subject SET subjectName = ?, credits = ? WHERE subjectID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getSubjectName());
            statement.setInt(2, subject.getCredits());
            statement.setInt(3, subject.getSubjectID());
            return statement.executeUpdate();
        }
    }

    // Xóa môn học
    public int deleteSubject(int subjectID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Subject WHERE subjectID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subjectID);
            return statement.executeUpdate();
        }
    }

    // Lấy danh sách tất cả môn học
    public List<Subject> getAllSubjects() throws SQLException, ClassNotFoundException {
        List<Subject> subjectList = new ArrayList<>();
        String sql = "SELECT subjectID, subjectName, credits FROM Subject";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(resultSet.getInt("subjectID"));
                subject.setSubjectName(resultSet.getString("subjectName"));
                subject.setCredits(resultSet.getInt("credits"));
                subjectList.add(subject);
            }
        }
        return subjectList;
    }

    // Lấy thông tin môn học theo ID
    public Subject getSubjectById(int subjectID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT subjectID, subjectName, credits FROM Subject WHERE subjectID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subjectID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectID(resultSet.getInt("subjectID"));
                    subject.setSubjectName(resultSet.getString("subjectName"));
                    subject.setCredits(resultSet.getInt("credits"));
                    return subject;
                }
            }
        }
        return null;
    }
}
