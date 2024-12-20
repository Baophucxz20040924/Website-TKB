package model.dao;

import model.bean.Schedule;
import model.dao.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class ScheduleDAO {

    // Thêm thời khóa biểu mới
    public int insertSchedule(Schedule schedule) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Schedule (StartTime, EndTime, DayOfWeek, RoomID, ClassID, SubjectID, LecturerID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, schedule.getStartTime());
            statement.setString(2, schedule.getEndTime());
            statement.setString(3, schedule.getDayOfWeek());
            statement.setInt(4, schedule.getRoomID());
            statement.setInt(5, schedule.getClassID());
            statement.setInt(6, schedule.getSubjectID());
            statement.setInt(7, schedule.getLecturerID());
            
            return statement.executeUpdate();
        }
    }

    // Cập nhật thời khóa biểu
    public int updateSchedule(Schedule schedule) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Schedule SET StartTime = ?, EndTime = ?, DayOfWeek = ?, RoomID = ?, ClassID = ?, SubjectID = ?, LecturerID = ? WHERE ScheduleID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, schedule.getStartTime());
            statement.setString(2, schedule.getEndTime());
            statement.setString(3, schedule.getDayOfWeek());
            statement.setInt(4, schedule.getRoomID());
            statement.setInt(5, schedule.getClassID());
            statement.setInt(6, schedule.getSubjectID());
            statement.setInt(7, schedule.getLecturerID());
            statement.setInt(8, schedule.getScheduleID());
            
            return statement.executeUpdate();
        }
    }

    // Xóa thời khóa biểu
    public int deleteSchedule(int scheduleID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Schedule WHERE ScheduleID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, scheduleID);
            return statement.executeUpdate();
        }
    }


    // Lấy thời khóa biểu theo ScheduleID
    public Schedule getScheduleById(int scheduleID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Schedule WHERE ScheduleID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, scheduleID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleID(resultSet.getInt("ScheduleID"));
                    schedule.setStartTime(resultSet.getString("StartTime"));
                    schedule.setEndTime(resultSet.getString("EndTime"));
                    schedule.setDayOfWeek(resultSet.getString("DayOfWeek"));
                    schedule.setRoomID(resultSet.getInt("RoomID"));
                    schedule.setClassID(resultSet.getInt("ClassID"));
                    schedule.setSubjectID(resultSet.getInt("SubjectID"));
                    schedule.setLecturerID(resultSet.getInt("LecturerID"));
                    return schedule;
                }
            }
        }
        return null;
    }

    // Lấy thời khóa biểu theo mã giảng viên
    public List<Schedule> getSchedulesByLecturerID(int lecturerID) throws SQLException, ClassNotFoundException {
        List<Schedule> scheduleList = new ArrayList<>();
        String sql = "SELECT * FROM Schedule WHERE LecturerID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, lecturerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleID(resultSet.getInt("ScheduleID"));
                    schedule.setStartTime(resultSet.getString("StartTime"));
                    schedule.setEndTime(resultSet.getString("EndTime"));
                    schedule.setDayOfWeek(resultSet.getString("DayOfWeek"));
                    schedule.setRoomID(resultSet.getInt("RoomID"));
                    schedule.setClassID(resultSet.getInt("ClassID"));
                    schedule.setSubjectID(resultSet.getInt("SubjectID"));
                    schedule.setLecturerID(resultSet.getInt("LecturerID"));
                    scheduleList.add(schedule);
                }
            }
        }
        return scheduleList;
    }
    // Lấy tất cả thời khóa biểu
    
    public List<Schedule> getAllSchedules() throws SQLException, ClassNotFoundException {
        List<Schedule> scheduleList = new ArrayList<>();
        String sql = "SELECT Schedule.scheduleID, Schedule.startTime, Schedule.endTime, Schedule.dayOfWeek, "
                   + "Schedule.roomID, Schedule.classID, Schedule.subjectID, Schedule.lecturerID, "
                   + "Subject.subjectName, Class.className, Room.roomName "
                   + "FROM Schedule "
                   + "JOIN Subject ON Schedule.subjectID = Subject.subjectID "
                   + "JOIN Class ON Schedule.classID = Class.classID "
                   + "JOIN Room ON Schedule.roomID = Room.roomID";

        try (Connection connection = ConnectDatabase.getMySQLConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleID(resultSet.getInt("scheduleID"));
                schedule.setStartTime(resultSet.getString("startTime"));
                schedule.setEndTime(resultSet.getString("endTime"));
                schedule.setDayOfWeek(resultSet.getString("dayOfWeek"));
                schedule.setRoomID(resultSet.getInt("roomID"));
                schedule.setClassID(resultSet.getInt("classID"));
                schedule.setSubjectID(resultSet.getInt("subjectID"));
                schedule.setLecturerID(resultSet.getInt("lecturerID"));
                schedule.setSubjectName(resultSet.getString("subjectName"));
                schedule.setClassName(resultSet.getString("className"));
                schedule.setRoomName(resultSet.getString("roomName"));

                scheduleList.add(schedule);
            }
        }

        return scheduleList;
    }
    public List<Map<String, String>> getScheduleDetailsByLecturerID(HttpServletRequest request)
            throws SQLException, ClassNotFoundException {
        String sql = "SELECT Schedule.DayOfWeek, Subject.SubjectName, Room.RoomName, Class.ClassName, "
                   + "Schedule.StartTime, Schedule.EndTime "
                   + "FROM Schedule "
                   + "JOIN Subject ON Schedule.SubjectID = Subject.SubjectID "
                   + "JOIN Room ON Schedule.RoomID = Room.RoomID "
                   + "JOIN Class ON Schedule.ClassID = Class.ClassID "
                   + "WHERE Schedule.LecturerID = ? "
                   + "ORDER BY FIELD(Schedule.DayOfWeek, 'Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ nhật')";

        List<Map<String, String>> scheduleDetails = new ArrayList<>();

        // Lấy lecturerID từ session
        HttpSession session = request.getSession();
        Integer lecturerID = (Integer) session.getAttribute("lecturerID");

        if (lecturerID == null) {
            throw new SQLException("Lecturer ID is not found in the session.");
        }

        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            
            statement.setInt(1, lecturerID); 
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Map<String, String> detail = new HashMap<>();

                 
                    System.out.println("DayOfWeek: " + rs.getString("DayOfWeek"));  // In ra giá trị DayOfWeek

                 
                    detail.put("DayOfWeek", rs.getString("DayOfWeek"));
                    detail.put("SubjectName", rs.getString("SubjectName") != null ? rs.getString("SubjectName") : "Không có môn học");
                    detail.put("RoomName", rs.getString("RoomName") != null ? rs.getString("RoomName") : "Không có phòng");
                    detail.put("ClassName", rs.getString("ClassName") != null ? rs.getString("ClassName") : "Không có lớp");
                    detail.put("StartTime", rs.getString("StartTime"));
                    detail.put("EndTime", rs.getString("EndTime"));

                  
                    scheduleDetails.add(detail);
                }
            }
        }
        return scheduleDetails;
    }

    public boolean isRoomAvailable(int roomID, String dayOfWeek, String startTime, String endTime) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM Schedule WHERE RoomID = ? AND DayOfWeek = ? AND " +
                     "(StartTime < ? AND EndTime > ?)";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomID);
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
    public boolean isClassAvailable(int classID, String dayOfWeek, String startTime, String endTime) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM Schedule WHERE ClassID = ? AND DayOfWeek = ? AND " +
                     "(StartTime < ? AND EndTime > ?)";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classID);
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
