package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bo.ScheduleBO;
import model.bean.Schedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListScheduleServlet", urlPatterns = {"/listSchedules"})
public class ListScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ScheduleBO scheduleBO;

    public ListScheduleServlet() {
        this.scheduleBO = new ScheduleBO(); // Khởi tạo đối tượng ScheduleBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy danh sách tất cả lịch học từ cơ sở dữ liệu
            List<Schedule> scheduleList = scheduleBO.getAllSchedules();

            // Đặt danh sách lịch học vào attribute của request
            request.setAttribute("scheduleList", scheduleList);

            // Chuyển tiếp request đến trang listSchedules.jsp để hiển thị danh sách
            request.getRequestDispatcher("listSchedules.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Nếu có lỗi, chuyển hướng đến trang lỗi
            request.setAttribute("message", "Lỗi khi lấy danh sách lịch học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
