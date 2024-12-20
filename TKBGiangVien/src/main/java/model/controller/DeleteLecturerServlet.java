package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.LecturerBO;

import java.io.IOException;

@WebServlet(name = "DeleteLecturerServlet", urlPatterns = {"/deleteLecturer"})
public class DeleteLecturerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LecturerBO lecturerBO;

    public DeleteLecturerServlet() {
        this.lecturerBO = new LecturerBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy LecturerID từ request
            String lecturerIDParam = request.getParameter("lecturerID");

            // Kiểm tra LecturerID có hợp lệ không
            if (lecturerIDParam == null || lecturerIDParam.isEmpty()) {
                throw new IllegalArgumentException("LecturerID is required.");
            }

            int lecturerID = Integer.parseInt(lecturerIDParam);

            // Xóa giảng viên bằng BO
            boolean isDeleted = lecturerBO.deleteLecturer(lecturerID);

            if (isDeleted) {
                // Nếu xóa thành công, chuyển hướng về danh sách giảng viên
                response.sendRedirect("listLecturers");
            } else {
                // Nếu xóa thất bại, hiển thị lỗi
                request.setAttribute("message", "Xóa giảng viên thất bại.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi
            request.setAttribute("message", "Đã xảy ra lỗi khi xóa giảng viên: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
