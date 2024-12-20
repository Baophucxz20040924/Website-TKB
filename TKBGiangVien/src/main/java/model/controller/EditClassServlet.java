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

@WebServlet(name = "EditClassServlet", urlPatterns = {"/editClass"})
public class EditClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClassBO classBO;

    public EditClassServlet() {
        this.classBO = new ClassBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classIDParam = request.getParameter("classID");

        try {
            // Kiểm tra nếu classIDParam là null hoặc rỗng
            if (classIDParam == null || classIDParam.isEmpty()) {
                throw new IllegalArgumentException("classID is null or empty.");
            }

            // Parse classID từ chuỗi sang số nguyên
            int classID = Integer.parseInt(classIDParam);

            // Lấy thông tin lớp học từ ClassBO
            Class clazz = classBO.getClassById(classID); // Đảm bảo phương thức này tồn tại trong ClassBO

            if (clazz == null) {
                throw new NullPointerException("Class not found for ID: " + classID);
            }

            // Gán thông tin lớp học vào request attribute
            request.setAttribute("clazz", clazz);

            // Chuyển tiếp tới trang chỉnh sửa lớp học
            request.getRequestDispatcher("editClassForm.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form chỉnh sửa
            String classIDParam = request.getParameter("classID");
            String className = request.getParameter("className");
            String numberOfStudentsParam = request.getParameter("numberOfStudents");

            // Kiểm tra dữ liệu đầu vào
            if (classIDParam == null || classIDParam.isEmpty()) {
                throw new IllegalArgumentException("ClassID is required.");
            }
            if (className == null || className.trim().isEmpty()) {
                throw new IllegalArgumentException("ClassName is required.");
            }
            if (numberOfStudentsParam == null || numberOfStudentsParam.isEmpty()) {
                throw new IllegalArgumentException("NumberOfStudents is required.");
            }

            // Parse dữ liệu từ chuỗi sang kiểu phù hợp
            int classID = Integer.parseInt(classIDParam);
            int numberOfStudents = Integer.parseInt(numberOfStudentsParam);

            // Tạo đối tượng Class và gán giá trị
            Class clazz = new Class();
            clazz.setClassID(classID);
            clazz.setClassName(className);
            clazz.setNumberOfStudents(numberOfStudents);

            // Gọi BO để cập nhật lớp học
            boolean isUpdated = classBO.updateClass(clazz);

            if (isUpdated) {
                // Nếu cập nhật thành công, chuyển hướng về danh sách lớp học
                response.sendRedirect("listClasses");
            } else {
                // Nếu cập nhật thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Cập nhật lớp học thất bại!");
                request.setAttribute("clazz", clazz); // Giữ lại dữ liệu để hiển thị lại form
                request.getRequestDispatcher("editClassForm.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi nếu có ngoại lệ
            request.setAttribute("message", "Đã xảy ra lỗi khi cập nhật lớp học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
