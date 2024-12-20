package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Schedule;
import model.bo.ScheduleBO;
import model.bo.RoomBO;
import model.bo.ClassBO;
import model.bo.LecturerBO;
import model.bean.Room;
import model.bean.Class;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddScheduleServlet", urlPatterns = {"/addSchedule"})
public class AddScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ScheduleBO scheduleBO;
    private RoomBO roomBO;
    private ClassBO classBO;
    private LecturerBO lecturerBO;

    public AddScheduleServlet() {
        this.scheduleBO = new ScheduleBO(); // Khởi tạo ScheduleBO
        this.roomBO = new RoomBO(); // Khởi tạo RoomBO
        this.classBO = new ClassBO(); // Khởi tạo ClassBO
        this.lecturerBO = new LecturerBO(); // Khởi tạo LecturerBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển tiếp đến form thêm thời khóa biểu
        request.getRequestDispatcher("addScheduleForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form
            String dayOfWeek = request.getParameter("dayOfWeek");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            int classID = Integer.parseInt(request.getParameter("classID"));
            int subjectID = Integer.parseInt(request.getParameter("subjectID"));
            int lecturerID = Integer.parseInt(request.getParameter("lecturerID"));

            // Kiểm tra dữ liệu đầu vào
            if (dayOfWeek == null || dayOfWeek.trim().isEmpty() ||
                startTime == null || startTime.trim().isEmpty() ||
                endTime == null || endTime.trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng nhập đầy đủ thông tin thời khóa biểu!");
            }

            // Kiểm tra sự trùng lặp giảng viên, phòng học và lớp học
            boolean isLecturerAvailable = lecturerBO.isLecturerAvailable(lecturerID, dayOfWeek, startTime, endTime);
            boolean isRoomAvailable = scheduleBO.isRoomAvailable(roomID, dayOfWeek, startTime, endTime);
            boolean isClassAvailable = scheduleBO.isClassAvailable(classID, dayOfWeek, startTime, endTime);

            // Kiểm tra xem giảng viên có sẵn vào thời gian này không
            if (!isLecturerAvailable) {
                request.setAttribute("message", "Giảng viên đã có lịch dạy vào thời gian này!");
                request.getRequestDispatcher("addScheduleForm.jsp").forward(request, response);
                return;
            }

            // Kiểm tra xem phòng học có trùng vào thời gian này không
            if (!isRoomAvailable) {
                request.setAttribute("message", "Phòng học đã được sử dụng vào thời gian này!");
                request.getRequestDispatcher("addScheduleForm.jsp").forward(request, response);
                return;
            }

            // Kiểm tra xem lớp học có trùng vào thời gian này không
            if (!isClassAvailable) {
                request.setAttribute("message", "Lớp học đã có lịch học vào thời gian này!");
                request.getRequestDispatcher("addScheduleForm.jsp").forward(request, response);
                return;
            }

            // Lấy thông tin phòng học và lớp học để kiểm tra sức chứa
            Room room = roomBO.getRoomById(roomID); // Lấy phòng học theo roomID
            Class cls = classBO.getClassById(classID); // Lấy lớp học theo classID

            // Kiểm tra sức chứa phòng học
            if (room != null && cls != null && room.getCapacity() < cls.getNumberOfStudents()) {
                request.setAttribute("message", "Phòng học không đủ sức chứa cho lớp học này!");
                request.getRequestDispatcher("addScheduleForm.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng Schedule
            Schedule schedule = new Schedule();
            schedule.setDayOfWeek(dayOfWeek);
            schedule.setStartTime(startTime);
            schedule.setEndTime(endTime);
            schedule.setRoomID(roomID);
            schedule.setClassID(classID);
            schedule.setSubjectID(subjectID);
            schedule.setLecturerID(lecturerID);

            // Gọi BO để thêm thời khóa biểu
            boolean isAdded = scheduleBO.addSchedule(schedule);

            if (isAdded) {
                // Nếu thêm thành công, chuyển hướng đến danh sách thời khóa biểu
                response.sendRedirect("listSchedules");
            } else {
                // Nếu thêm thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Thêm thời khóa biểu thất bại!");
                request.getRequestDispatcher("addScheduleForm.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Mã phòng học, lớp học, môn học và giảng viên phải là số nguyên hợp lệ.");
            request.getRequestDispatcher("addScheduleForm.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("addScheduleForm.jsp").forward(request, response);
        }
    }
}
