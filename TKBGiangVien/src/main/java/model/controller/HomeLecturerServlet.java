package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.bean.Account;

import java.io.IOException;

@WebServlet(name = "HomeLecturerServlet", urlPatterns = {"/homeLecturer"})
public class HomeLecturerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin tài khoản từ session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null || !"Lecturer".equals(account.getRole())) {
            // Nếu không có tài khoản hoặc không phải giảng viên, chuyển hướng về trang login
            response.sendRedirect("login.jsp");
            return;
        }

        // Đặt thông tin giảng viên vào request attribute để hiển thị trong JSP
        request.setAttribute("lecturerName", account.getUsername());

        // Chuyển tiếp đến trang homeLecturer.jsp
        request.getRequestDispatcher("homeLecturer.jsp").forward(request, response);
    }
}
