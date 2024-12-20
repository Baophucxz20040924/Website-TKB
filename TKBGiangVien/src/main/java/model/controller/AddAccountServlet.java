package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bo.AccountBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddAccountServlet", urlPatterns = {"/addAccount"})
public class AddAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Tạo đối tượng AccountBO để xử lý logic
    private AccountBO accountBO;

    public AddAccountServlet() {
        this.accountBO = new AccountBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến form thêm tài khoản (addAccountForm.jsp)
        request.getRequestDispatcher("addAccountForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        boolean isActive = request.getParameter("isActive") != null; // Checkbox trả về null nếu không được chọn

        // Tạo đối tượng Account với dữ liệu nhận được
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        account.setActive(isActive);

        try {
            // Gọi phương thức addAccount để thêm tài khoản và lấy AccountID
            int accountID = accountBO.addAccount(account);

            if (accountID > 0) {
                // Nếu thêm thành công, hiển thị thông báo thành công và AccountID
                request.setAttribute("message", "Thêm tài khoản thành công! Mã tài khoản: " + accountID);
            } else {
                // Nếu thêm thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Thêm tài khoản thất bại!");
            }

            // Quay lại trang form thêm tài khoản
            request.getRequestDispatcher("addAccountForm.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Hiển thị thông báo lỗi nếu xảy ra lỗi
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("addAccountForm.jsp").forward(request, response);
        }
    }
}
