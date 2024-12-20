package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.bean.Account;

import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy session và kiểm tra vai trò
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            // Nếu chưa đăng nhập, chuyển hướng về login.jsp
            response.sendRedirect("login.jsp");
            return;
        }

        if (!"Admin".equals(account.getRole())) {
            // Nếu không phải Admin, gửi thông báo lỗi
            request.setAttribute("message", "Bạn không có quyền truy cập trang này!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Nếu là Admin, chuyển đến trang home.jsp
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
}
