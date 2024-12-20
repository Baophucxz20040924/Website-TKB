package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Schedule;
import model.bo.ScheduleBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditScheduleServlet", urlPatterns = {"/editSchedule"})
public class EditScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ScheduleBO scheduleBO;

    public EditScheduleServlet() {
        this.scheduleBO = new ScheduleBO(); // Khởi tạo ScheduleBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String scheduleIDParam = request.getParameter("scheduleID");
        int scheduleID;

        try {
            scheduleID = Integer.parseInt(scheduleIDParam);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "ID thời khóa biểu không hợp lệ!");
            request.getRequestDispatcher("listSchedules").forward(request, response);
            return;
        }

        try {
            // Lấy thông tin thời khóa biểu từ ScheduleBO
            Schedule schedule = scheduleBO.getScheduleById(scheduleID);

            if (schedule != null) {
                // Đặt đối tượng schedule vào request để sử dụng trong JSP
                request.setAttribute("schedule", schedule);
                request.getRequestDispatcher("editScheduleForm.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Không tìm thấy thời khóa biểu!");
                request.getRequestDispatcher("listSchedules").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi khi tải thông tin thời khóa biểu: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form chỉnh sửa
        String scheduleIDParam = request.getParameter("scheduleID");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String dayOfWeek = request.getParameter("dayOfWeek");
        String roomIDParam = request.getParameter("roomID");
        String classIDParam = request.getParameter("classID");
        String subjectIDParam = request.getParameter("subjectID");
        String lecturerIDParam = request.getParameter("lecturerID");

        // Kiểm tra các dữ liệu đầu vào
        if (scheduleIDParam == null || scheduleIDParam.isEmpty()) {
            request.setAttribute("message", "ScheduleID không được để trống!");
            request.getRequestDispatcher("editScheduleForm.jsp").forward(request, response);
            return;
        }

        try {
            int scheduleID = Integer.parseInt(scheduleIDParam);
            int roomID = Integer.parseInt(roomIDParam);
            int classID = Integer.parseInt(classIDParam);
            int subjectID = Integer.parseInt(subjectIDParam);
            int lecturerID = Integer.parseInt(lecturerIDParam);

            // Tạo đối tượng Schedule và cập nhật giá trị
            Schedule schedule = new Schedule();
            schedule.setScheduleID(scheduleID);
            schedule.setStartTime(startTime);
            schedule.setEndTime(endTime);
            schedule.setDayOfWeek(dayOfWeek);
            schedule.setRoomID(roomID);
            schedule.setClassID(classID);
            schedule.setSubjectID(subjectID);
            schedule.setLecturerID(lecturerID);

            // Gọi BO để cập nhật thông tin thời khóa biểu
            boolean isUpdated = scheduleBO.updateSchedule(schedule);

            if (isUpdated) {
                // Nếu cập nhật thành công, chuyển về danh sách thời khóa biểu
                response.sendRedirect("listSchedules");
            } else {
                // Nếu cập nhật thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Cập nhật thời khóa biểu thất bại!");
                request.getRequestDispatcher("editScheduleForm.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Các trường không hợp lệ. Vui lòng kiểm tra lại!");
            request.getRequestDispatcher("editScheduleForm.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi khi cập nhật thời khóa biểu: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
