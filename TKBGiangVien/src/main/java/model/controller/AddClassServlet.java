package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Class;
import model.bo.ClassBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddClassServlet", urlPatterns = {"/addClass"})
public class AddClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Tạo đối tượng ClassBO để xử lý logic
    private ClassBO classBO;

    public AddClassServlet() {
        this.classBO = new ClassBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến form thêm lớp học (addClassForm.jsp)
        request.getRequestDispatcher("addClassForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String className = request.getParameter("className");
        int numberOfStudents = 0;

        try {
            numberOfStudents = Integer.parseInt(request.getParameter("numberOfStudents"));
        } catch (NumberFormatException e) {
            // Nếu số lượng sinh viên không phải là số hợp lệ, hiển thị lỗi
            request.setAttribute("message", "Số lượng sinh viên phải là một số nguyên hợp lệ!");
            request.getRequestDispatcher("addClassForm.jsp").forward(request, response);
            return;
        }

        // Tạo đối tượng Class với dữ liệu nhận được
        Class cls = new Class();
        cls.setClassName(className);
        cls.setNumberOfStudents(numberOfStudents);

        try {
            // Gọi phương thức addClass để thêm lớp học và lấy classID
            boolean isAdded = classBO.addClass(cls);

            if (isAdded) {
                // Nếu thêm thành công, hiển thị thông báo thành công
                request.setAttribute("message", "Thêm lớp học thành công!");
            } else {
                // Nếu thêm thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Thêm lớp học thất bại!");
            }

            // Quay lại trang form thêm lớp học
            request.getRequestDispatcher("addClassForm.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Hiển thị thông báo lỗi nếu xảy ra lỗi
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("addClassForm.jsp").forward(request, response);
        }
    }
}

