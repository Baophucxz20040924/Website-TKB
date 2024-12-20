package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.bo.ScheduleBO;
import model.bean.Schedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListScheduleLecturerIDServlet", urlPatterns = {"/listScheduleLecturer"})
public class ListScheduleLecturerIDServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ScheduleBO scheduleBO;

    public ListScheduleLecturerIDServlet() {
        this.scheduleBO = new ScheduleBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy lecturerID từ session
            HttpSession session = request.getSession();
            Integer lecturerID = (Integer) session.getAttribute("lecturerID");

            // In ra console để kiểm tra giá trị lecturerID
            System.out.println("Lecturer ID from session: " + lecturerID);  // In ID giảng viên từ session

            if (lecturerID == null) {
                // Nếu không tìm thấy lecturerID trong session, chuyển hướng về trang login
                System.out.println("Lecturer ID not found in session. Redirecting to login page.");
                response.sendRedirect("login.jsp");
                return;
            }

            // Lấy danh sách thời khóa biểu của giảng viên
            List<Schedule> scheduleList = scheduleBO.getSchedulesByLecturerID(lecturerID);

            // In ra chi tiết thời khóa biểu
            for (Schedule schedule : scheduleList) {
                // In thông tin chi tiết của từng thời khóa biểu
                System.out.println("Ngày: " + schedule.getDayOfWeek());  // In thông tin về ngày
                System.out.println("Môn Học: " + schedule.getSubjectName());  // In tên môn học
                System.out.println("Lớp Học: " + schedule.getClassName());  // In tên lớp học
                System.out.println("Phòng Học: " + schedule.getRoomName());  // In tên phòng học
                System.out.println("Thời Gian: " + schedule.getStartTime() + " - " + schedule.getEndTime());  // In thời gian
                System.out.println("=========================================");  // Dấu phân cách các bản ghi
            }

            // Đưa danh sách thời khóa biểu vào request
            request.setAttribute("scheduleList", scheduleList);

            // Chuyển tiếp đến JSP để hiển thị danh sách thời khóa biểu
            request.getRequestDispatcher("listScheduleLecturer.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // In ra thông tin lỗi nếu có
            System.out.println("Error fetching schedule data: " + e.getMessage());  // In lỗi vào console
            request.setAttribute("message", "Lỗi khi lấy danh sách thời khóa biểu: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
