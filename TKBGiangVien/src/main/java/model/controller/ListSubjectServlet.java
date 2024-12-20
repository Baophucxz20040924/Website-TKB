package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Subject;
import model.bo.SubjectBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ListSubjectServlet", urlPatterns = {"/listSubjects"})
public class ListSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Tạo đối tượng SubjectBO để sử dụng các phương thức quản lý môn học
    private SubjectBO subjectBO;

    public ListSubjectServlet() {
        this.subjectBO = new SubjectBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy danh sách môn học từ cơ sở dữ liệu
            ArrayList<Subject> subjectList = (ArrayList<Subject>) subjectBO.getAllSubjects();

            // Đặt danh sách môn học vào request attribute
            request.setAttribute("subjectList", subjectList);

            // In ra console để kiểm tra dữ liệu
            System.out.println("Danh sách môn học:");
            for (Subject subject : subjectList) {
                System.out.println(subject.getSubjectName());
            }

            // Chuyển tiếp request tới JSP để hiển thị danh sách môn học
            request.getRequestDispatcher("listSubjects.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi nếu có lỗi xảy ra
            request.setAttribute("message", "Lỗi khi lấy danh sách môn học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
