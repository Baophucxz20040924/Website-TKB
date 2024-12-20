package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bo.ScheduleBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteScheduleServlet", urlPatterns = {"/deleteSchedule"})
public class DeleteScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ScheduleBO scheduleBO;

    public DeleteScheduleServlet() {
        this.scheduleBO = new ScheduleBO(); // Khởi tạo đối tượng ScheduleBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy ScheduleID từ tham số URL
        String scheduleIDParam = request.getParameter("scheduleID");
        int scheduleID;

        try {
            scheduleID = Integer.parseInt(scheduleIDParam); // Chuyển đổi ScheduleID sang số nguyên
        } catch (NumberFormatException e) {
            // Nếu ScheduleID không hợp lệ, quay lại danh sách lịch học
            request.setAttribute("message", "ID lịch học không hợp lệ!");
            request.getRequestDispatcher("listSchedules").forward(request, response);
            return;
        }

        try {
            // Gọi phương thức deleteSchedule từ ScheduleBO
            boolean isDeleted = scheduleBO.deleteSchedule(scheduleID);

            if (isDeleted) {
                // Nếu xóa thành công, chuyển hướng về danh sách lịch học
                request.setAttribute("message", "Xóa lịch học thành công!");
            } else {
                // Nếu xóa thất bại, hiển thị lỗi
                request.setAttribute("message", "Xóa lịch học thất bại! ID không tồn tại.");
            }

            // Chuyển hướng về danh sách lịch học
            request.getRequestDispatcher("listSchedules").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Hiển thị lỗi nếu có lỗi từ database
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("listSchedules").forward(request, response);
        }
    }
}
