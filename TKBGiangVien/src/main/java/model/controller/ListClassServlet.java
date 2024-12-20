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
import java.util.ArrayList;

@WebServlet(name = "ListClassServlet", urlPatterns = {"/listClasses"})
public class ListClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Tạo đối tượng ClassBO để sử dụng các phương thức quản lý lớp học
    private ClassBO classBO;

    public ListClassServlet() {
        this.classBO = new ClassBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy danh sách lớp học từ cơ sở dữ liệu
        	ArrayList<Class> classList = (ArrayList<Class>) classBO.getAllClasses();

            // Đặt danh sách lớp học vào request attribute
            request.setAttribute("classList", classList);
            System.out.println("Danh sách lớp học:");
            for (Class cls : classList) {
                System.out.println(cls.getClassName());
            }

            // Chuyển tiếp request tới JSP để hiển thị danh sách lớp học
            request.getRequestDispatcher("listClasses.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi nếu có lỗi xảy ra
            request.setAttribute("message", "Lỗi khi lấy danh sách lớp học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
}

