package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bo.ClassBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteClassServlet", urlPatterns = {"/deleteClass"})
public class DeleteClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClassBO classBO;

    public DeleteClassServlet() {
        this.classBO = new ClassBO(); // Khởi tạo đối tượng ClassBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy classID từ tham số URL
        String classIDParam = request.getParameter("classID");
        int classID;

        try {
            classID = Integer.parseInt(classIDParam); // Chuyển đổi classID sang kiểu số nguyên
        } catch (NumberFormatException e) {
            // Nếu classID không hợp lệ, hiển thị lỗi và quay lại danh sách lớp học
            request.setAttribute("message", "ID lớp học không hợp lệ!");
            request.getRequestDispatcher("listClasses").forward(request, response);
            return;
        }

        try {
            // Gọi phương thức deleteClass từ ClassBO
            boolean isDeleted = classBO.deleteClass(classID);

            if (isDeleted) {
                // Nếu xóa thành công, chuyển hướng về danh sách lớp học
                request.setAttribute("message", "Xóa lớp học thành công!");
            } else {
                // Nếu xóa thất bại, hiển thị lỗi
                request.setAttribute("message", "Xóa lớp học thất bại! ID không tồn tại.");
            }

            // Chuyển hướng về danh sách lớp học
            request.getRequestDispatcher("listClasses").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Hiển thị lỗi nếu có lỗi từ database
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("listClasses").forward(request, response);
        }
    }
}
