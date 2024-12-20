package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.bean.Account;
import model.bean.Lecturer;
import model.dao.AccountDAO;
import model.dao.LecturerDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;
    private LecturerDAO lecturerDAO;

    public LoginServlet() {
        this.accountDAO = new AccountDAO();
        this.lecturerDAO = new LecturerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị trang đăng nhập khi có yêu cầu GET
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Kiểm tra tài khoản trong cơ sở dữ liệu
            Account account = accountDAO.findAccountByUsername(username);

            if (account != null && account.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("account", account);

                // Lấy thông tin giảng viên từ cơ sở dữ liệu
                if ("Lecturer".equals(account.getRole())) {
                    Lecturer lecturer = lecturerDAO.getLecturerByAccountID(account.getAccountID());
                    session.setAttribute("lecturerID", lecturer.getLecturerID());
                    session.setAttribute("lecturerName", lecturer.getName());
                }

                // Điều hướng theo vai trò
                if ("Admin".equals(account.getRole())) {
                    response.sendRedirect("homepage.jsp");
                } else if ("Lecturer".equals(account.getRole())) {
                    response.sendRedirect("homeLecturer.jsp");
                }
            } else {
                // Nếu đăng nhập thất bại, quay lại trang login với thông báo lỗi
                request.setAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
