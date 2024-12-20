package model.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


import model.bean.Account;
import model.bean.Lecturer;
import model.bo.LecturerBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddLecturerServlet", urlPatterns = {"/addLecturer"})
public class AddLecturerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Tạo đối tượng LecturerBO để xử lý logic
    private LecturerBO lecturerBO;

    public AddLecturerServlet() {
        this.lecturerBO = new LecturerBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy danh sách tài khoản chưa được sử dụng
            List<Account> unusedAccounts = lecturerBO.getUnusedAccounts();

            // Gửi danh sách sang form JSP
            request.setAttribute("accountList", unusedAccounts);
            request.getRequestDispatcher("addLecturerForm.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi khi lấy danh sách tài khoản: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int accountID = 0;

        try {
            accountID = Integer.parseInt(request.getParameter("accountID"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Mã tài khoản phải là một số nguyên hợp lệ!");
            request.getRequestDispatcher("addLecturerForm.jsp").forward(request, response);
            return;
        }

        Account account = new Account();
        account.setAccountID(accountID);

        Lecturer lecturer = new Lecturer();
        lecturer.setName(name);
        lecturer.setEmail(email);
        lecturer.setAccount(account);

        try {
            boolean isAdded = lecturerBO.addLecturer(lecturer);

            if (isAdded) {
                request.setAttribute("message", "Thêm giảng viên thành công!");
            } else {
                request.setAttribute("message", "Tài khoản đã được gán cho một giảng viên khác. Vui lòng chọn tài khoản khác.");
            }

            request.getRequestDispatcher("addLecturerForm.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("addLecturerForm.jsp").forward(request, response);
        }
    }

}
