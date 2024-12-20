package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bo.SubjectBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteSubjectServlet", urlPatterns = {"/deleteSubject"})
public class DeleteSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SubjectBO subjectBO;

    public DeleteSubjectServlet() {
        this.subjectBO = new SubjectBO(); // Khởi tạo đối tượng SubjectBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy subjectID từ tham số URL
        String subjectIDParam = request.getParameter("subjectID");
        int subjectID;

        try {
            // Chuyển đổi subjectID sang kiểu số nguyên
            subjectID = Integer.parseInt(subjectIDParam);
        } catch (NumberFormatException e) {
            // Nếu subjectID không hợp lệ, hiển thị lỗi và quay lại danh sách môn học
            request.setAttribute("message", "ID môn học không hợp lệ!");
            request.getRequestDispatcher("listSubjects").forward(request, response);
            return;
        }

        try {
            // Gọi phương thức deleteSubject từ SubjectBO
            boolean isDeleted = subjectBO.deleteSubject(subjectID);

            if (isDeleted) {
                // Nếu xóa thành công, chuyển hướng về danh sách môn học
                request.setAttribute("message", "Xóa môn học thành công!");
            } else {
                // Nếu xóa thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Xóa môn học thất bại! ID không tồn tại.");
            }

            // Chuyển tiếp về trang danh sách môn học
            request.getRequestDispatcher("listSubjects").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Hiển thị lỗi nếu có lỗi từ database
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("listSubjects").forward(request, response);
        }
    }
}
