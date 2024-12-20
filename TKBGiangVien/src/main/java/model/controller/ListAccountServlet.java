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
import java.util.ArrayList;

@WebServlet(name = "ListAccountServlet", urlPatterns = {"/listAccounts"})
public class ListAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Tạo đối tượng AccountBO để sử dụng các phương thức quản lý tài khoản
    private AccountBO accountBO;

    public ListAccountServlet() {
        this.accountBO = new AccountBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy danh sách tài khoản từ cơ sở dữ liệu
            ArrayList<Account> accountList = accountBO.getAllAccounts();

            // Đặt danh sách tài khoản vào request attribute
            request.setAttribute("accountList", accountList);
            System.out.println("Danh sách tài khoản:");
            for (Account account : accountList) {
                System.out.println(account.getUsername());
            }


            // Chuyển tiếp request tới JSP để hiển thị danh sách tài khoản
            request.getRequestDispatcher("listAccounts.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi nếu có lỗi xảy ra
            request.setAttribute("message", "Lỗi khi lấy danh sách tài khoản: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
