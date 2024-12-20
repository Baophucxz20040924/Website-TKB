package model.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bo.AccountBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/deleteAccount"})
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountBO accountBO;

    public DeleteAccountServlet() {
        super();
        accountBO = new AccountBO(); // Khởi tạo AccountBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy AccountID từ tham số của request
        String accountIdParam = request.getParameter("accountID");

        try {
            if (accountIdParam != null && !accountIdParam.isEmpty()) {
                int accountID = Integer.parseInt(accountIdParam);

                // Xóa tài khoản bằng AccountBO
                boolean isDeleted = accountBO.deleteAccount(accountID);

                if (isDeleted) {
                    // Nếu xóa thành công, chuyển hướng về danh sách tài khoản với thông báo
                    request.setAttribute("message", "Xóa tài khoản thành công!");
                } else {
                    // Nếu xóa thất bại
                    request.setAttribute("message", "Không thể xóa tài khoản. Vui lòng thử lại!");
                }
            } else {
                // Nếu không tìm thấy ID
                request.setAttribute("message", "Không tìm thấy tài khoản để xóa!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Xử lý lỗi và chuyển hướng về trang lỗi
            request.setAttribute("message", "Đã xảy ra lỗi khi xóa tài khoản: " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Lỗi nếu AccountID không phải số
            request.setAttribute("message", "ID tài khoản không hợp lệ!");
        }

        // Quay về danh sách tài khoản sau khi thực hiện xóa
        request.getRequestDispatcher("listAccounts").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Sử dụng phương thức doGet để xử lý POST
    }
}
