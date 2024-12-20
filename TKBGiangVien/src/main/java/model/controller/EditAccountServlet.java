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

@WebServlet(name = "EditAccountServlet", urlPatterns = {"/editAccount"})
public class EditAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountBO accountBO;

    public EditAccountServlet() {
        this.accountBO = new AccountBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));

        try {
            // Lấy thông tin tài khoản từ BO
            Account account = accountBO.getAllAccounts().stream()
                    .filter(acc -> acc.getAccountID() == accountID)
                    .findFirst()
                    .orElse(null);

            if (account == null) {
                // Nếu không tìm thấy tài khoản, chuyển hướng đến trang lỗi
                response.sendRedirect("error.jsp");
                return;
            }

            // Đưa thông tin tài khoản vào request
            request.setAttribute("account", account);

            // Chuyển đến trang form chỉnh sửa
            request.getRequestDispatcher("editAccountForm.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi và chuyển hướng đến trang lỗi
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form chỉnh sửa
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        boolean isActive = request.getParameter("isActive") != null;

        // Tạo đối tượng Account để cập nhật
        Account account = new Account();
        account.setAccountID(accountID);
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        account.setActive(isActive);

        try {
            // Gọi BO để cập nhật tài khoản
            boolean isUpdated = accountBO.updateAccount(account);

            if (isUpdated) {
                // Nếu cập nhật thành công, chuyển hướng về danh sách tài khoản
                response.sendRedirect("listAccounts");
            } else {
                // Nếu thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Cập nhật tài khoản thất bại!");
                request.getRequestDispatcher("editAccountForm.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi và chuyển hướng đến trang lỗi
            response.sendRedirect("error.jsp");
        }
    }
}
