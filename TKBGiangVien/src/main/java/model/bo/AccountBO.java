package model.bo;

import model.bean.Account;
import model.dao.AccountDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountBO {

    // Tạo đối tượng AccountDAO để sử dụng các phương thức liên quan đến cơ sở dữ liệu
    private AccountDAO accountDAO = new AccountDAO();

    // Thêm tài khoản
    public int addAccount(Account account) throws SQLException, ClassNotFoundException {
        return accountDAO.insertAccount(account); // Trả về AccountID vừa tạo hoặc -1 nếu thất bại
    }
    // Sửa tài khoản
    public boolean updateAccount(Account account) throws SQLException, ClassNotFoundException {
        int result = accountDAO.updateAccount(account);
        return result > 0; // Trả về true nếu cập nhật thành công
    }

    // Xóa tài khoản
    public boolean deleteAccount(int accountID) throws SQLException, ClassNotFoundException {
        int result = accountDAO.deleteAccount(accountID);
        return result > 0; // Trả về true nếu xóa thành công
    }

    // Lấy danh sách tất cả tài khoản
    public ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException {
        return accountDAO.getAllAccounts(); // Gọi phương thức từ AccountDAO
    }

    // Hiển thị danh sách tài khoản (nâng cao - để test trong console)
    public void displayAllAccounts() throws SQLException, ClassNotFoundException {
        ArrayList<Account> accounts = accountDAO.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("Không có tài khoản nào trong danh sách.");
        } else {
            System.out.println("Danh sách tài khoản:");
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }
}
