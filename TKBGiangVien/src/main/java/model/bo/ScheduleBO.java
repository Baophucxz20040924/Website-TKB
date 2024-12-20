package model.bo;

import model.bean.Schedule;

import model.dao.ScheduleDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class ScheduleBO {
    private ScheduleDAO scheduleDAO;

    public ScheduleBO() {
        scheduleDAO = new ScheduleDAO(); // Khởi tạo đối tượng ScheduleDAO
    }

    // Thêm thời khóa biểu mới
    public boolean addSchedule(Schedule schedule) throws SQLException, ClassNotFoundException {
        int result = scheduleDAO.insertSchedule(schedule);
        return result > 0; // Trả về true nếu thêm thành công
    }

    // Cập nhật thời khóa biểu
    public boolean updateSchedule(Schedule schedule) throws SQLException, ClassNotFoundException {
        int result = scheduleDAO.updateSchedule(schedule);
        return result > 0; // Trả về true nếu cập nhật thành công
    }

    // Xóa thời khóa biểu
    public boolean deleteSchedule(int scheduleID) throws SQLException, ClassNotFoundException {
        int result = scheduleDAO.deleteSchedule(scheduleID);
        return result > 0; // Trả về true nếu xóa thành công
    }

    // Lấy danh sách tất cả thời khóa biểu
    public List<Schedule> getAllSchedules() throws SQLException, ClassNotFoundException {
        return scheduleDAO.getAllSchedules();
    }

    // Lấy thời khóa biểu theo ID
    public Schedule getScheduleById(int scheduleID) throws SQLException, ClassNotFoundException {
        return scheduleDAO.getScheduleById(scheduleID);
    }

    // Lấy thời khóa biểu cho một giảng viên cụ thể
    public List<Schedule> getSchedulesByLecturerID(int lecturerID) throws SQLException, ClassNotFoundException {
        return scheduleDAO.getSchedulesByLecturerID(lecturerID);
    }

    // Kiểm tra phòng học có sẵn vào thời gian không
    public boolean isRoomAvailable(int roomID, String dayOfWeek, String startTime, String endTime) throws SQLException, ClassNotFoundException {
        return scheduleDAO.isRoomAvailable(roomID, dayOfWeek, startTime, endTime);
    }

    // Kiểm tra lớp học có sẵn vào thời gian không
    public boolean isClassAvailable(int classID, String dayOfWeek, String startTime, String endTime) throws SQLException, ClassNotFoundException {
        return scheduleDAO.isClassAvailable(classID, dayOfWeek, startTime, endTime);
    }

    // Lấy chi tiết thời khóa biểu theo scheduleID
    public List<Map<String, String>> getScheduleDetailsByLecturerID(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        // Call the DAO method to get the schedule details by lecturer ID
        return scheduleDAO.getScheduleDetailsByLecturerID(request);
    }
}
