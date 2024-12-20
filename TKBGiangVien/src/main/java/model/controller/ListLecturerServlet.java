package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Lecturer;
import model.bo.LecturerBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListLecturerServlet", urlPatterns = {"/listLecturers"})
public class ListLecturerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LecturerBO lecturerBO;

    public ListLecturerServlet() {
        this.lecturerBO = new LecturerBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy danh sách giảng viên từ BO
            List<Lecturer> lecturerList = lecturerBO.getAllLecturers();

            // Gửi danh sách giảng viên đến JSP
            request.setAttribute("lecturerList", lecturerList);
            request.getRequestDispatcher("listLecturers.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi khi tải danh sách giảng viên: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
