package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Subject;
import model.bo.SubjectBO;

import java.io.IOException;

@WebServlet(name = "EditSubjectServlet", urlPatterns = {"/editSubject"})
public class EditSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SubjectBO subjectBO;

    public EditSubjectServlet() {
        this.subjectBO = new SubjectBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subjectIDParam = request.getParameter("subjectID");

        try {
            // Kiểm tra nếu subjectIDParam là null hoặc rỗng
            if (subjectIDParam == null || subjectIDParam.isEmpty()) {
                throw new IllegalArgumentException("subjectID is null or empty.");
            }

            // Parse subjectID từ chuỗi sang số nguyên
            int subjectID = Integer.parseInt(subjectIDParam);

            // Lấy thông tin môn học từ SubjectBO
            Subject subject = subjectBO.getSubjectById(subjectID); // Đảm bảo phương thức này tồn tại trong SubjectBO

            if (subject == null) {
                throw new NullPointerException("Subject not found for ID: " + subjectID);
            }

            // Gán thông tin môn học vào request attribute
            request.setAttribute("subject", subject);

            // Chuyển tiếp tới trang chỉnh sửa môn học
            request.getRequestDispatcher("editSubjectForm.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi
            request.setAttribute("message", "Lỗi khi tải thông tin môn học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form chỉnh sửa
            String subjectIDParam = request.getParameter("subjectID");
            String subjectName = request.getParameter("subjectName");
            String creditsParam = request.getParameter("credits");

            // Kiểm tra dữ liệu đầu vào
            if (subjectIDParam == null || subjectIDParam.isEmpty()) {
                throw new IllegalArgumentException("SubjectID is required.");
            }
            if (subjectName == null || subjectName.trim().isEmpty()) {
                throw new IllegalArgumentException("SubjectName is required.");
            }
            if (creditsParam == null || creditsParam.isEmpty()) {
                throw new IllegalArgumentException("Credits is required.");
            }

            // Parse dữ liệu từ chuỗi sang kiểu phù hợp
            int subjectID = Integer.parseInt(subjectIDParam);
            int credits = Integer.parseInt(creditsParam);

            // Tạo đối tượng Subject và gán giá trị
            Subject subject = new Subject();
            subject.setSubjectID(subjectID);
            subject.setSubjectName(subjectName);
            subject.setCredits(credits);

            // Gọi BO để cập nhật môn học
            boolean isUpdated = subjectBO.updateSubject(subject);

            if (isUpdated) {
                // Nếu cập nhật thành công, chuyển hướng về danh sách môn học
                response.sendRedirect("listSubjects");
            } else {
                // Nếu cập nhật thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Cập nhật môn học thất bại!");
                request.setAttribute("subject", subject); // Giữ lại dữ liệu để hiển thị lại form
                request.getRequestDispatcher("editSubjectForm.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi nếu có ngoại lệ
            request.setAttribute("message", "Đã xảy ra lỗi khi cập nhật môn học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
