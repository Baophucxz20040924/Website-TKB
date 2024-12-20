package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Lecturer;
import model.bo.LecturerBO;
import model.bean.Account;


import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EditLecturerServlet", urlPatterns = {"/editLecturer"})
public class EditLecturerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LecturerBO lecturerBO;

    public EditLecturerServlet() {
        this.lecturerBO = new LecturerBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lecturerIDParam = request.getParameter("lecturerID");

        try {
            // Kiểm tra nếu lecturerIDParam null hoặc rỗng
            if (lecturerIDParam == null || lecturerIDParam.isEmpty()) {
                throw new IllegalArgumentException("LecturerID is required.");
            }

            // Parse lecturerID
            int lecturerID = Integer.parseInt(lecturerIDParam);

            // Lấy thông tin giảng viên từ LecturerBO
            Lecturer lecturer = lecturerBO.getLecturerById(lecturerID); // Đảm bảo phương thức này tồn tại

            if (lecturer == null) {
                throw new NullPointerException("Lecturer not found for ID: " + lecturerID);
            }

            // Gán thông tin giảng viên vào request attribute
            request.setAttribute("lecturer", lecturer);

            // Gửi danh sách tài khoản chưa sử dụng
            request.setAttribute("accountList", lecturerBO.getUnusedAccounts());

            // Chuyển tiếp tới trang JSP
            request.getRequestDispatcher("editLecturerForm.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi
            request.setAttribute("message", "Lỗi khi tải thông tin giảng viên: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form
            int lecturerID = Integer.parseInt(request.getParameter("lecturerID"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int accountID = Integer.parseInt(request.getParameter("accountID"));

            // Kiểm tra dữ liệu hợp lệ
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name is required.");
            }
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email is required.");
            }

            // Tạo đối tượng Lecturer
            Lecturer lecturer = new Lecturer();
            lecturer.setLecturerID(lecturerID);
            lecturer.setName(name);
            lecturer.setEmail(email);

            // Tạo đối tượng Account liên kết
            lecturer.setAccount(new Account(accountID, null, null, null, false));

            // Gọi BO để cập nhật
            boolean isUpdated = lecturerBO.updateLecturer(lecturer);

            if (isUpdated) {
                response.sendRedirect("listLecturers"); // Chuyển hướng về danh sách giảng viên
            } else {
                throw new Exception("Cập nhật thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi khi cập nhật giảng viên: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
