package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Subject;
import model.bo.SubjectBO;

import java.io.IOException;

@WebServlet(name = "AddSubjectServlet", urlPatterns = {"/addSubject"})
public class AddSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SubjectBO subjectBO;

    public AddSubjectServlet() {
        this.subjectBO = new SubjectBO(); // Khởi tạo đối tượng SubjectBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến form thêm môn học
        request.getRequestDispatcher("addSubjectForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form
            String subjectName = request.getParameter("subjectName");
            String creditsParam = request.getParameter("credits");

            // Kiểm tra dữ liệu đầu vào
            if (subjectName == null || subjectName.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên môn học không được để trống.");
            }
            if (creditsParam == null || creditsParam.isEmpty()) {
                throw new IllegalArgumentException("Số tín chỉ không được để trống.");
            }

            // Chuyển đổi số tín chỉ thành kiểu int
            int credits = Integer.parseInt(creditsParam);

            // Tạo đối tượng Subject
            Subject subject = new Subject();
            subject.setSubjectName(subjectName);
            subject.setCredits(credits);

            // Thêm môn học bằng SubjectBO
            boolean isAdded = subjectBO.addSubject(subject);

            if (isAdded) {
                // Chuyển hướng về trang danh sách môn học nếu thành công
                response.sendRedirect("listSubjects");
            } else {
                // Gửi thông báo lỗi nếu thất bại
                request.setAttribute("message", "Thêm môn học thất bại!");
                request.getRequestDispatcher("addSubjectForm.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu số tín chỉ không hợp lệ
            request.setAttribute("message", "Số tín chỉ phải là một số nguyên hợp lệ.");
            request.getRequestDispatcher("addSubjectForm.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("addSubjectForm.jsp").forward(request, response);
        }
    }
}
