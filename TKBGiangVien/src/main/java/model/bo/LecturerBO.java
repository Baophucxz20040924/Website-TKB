package model.bo;

import model.bean.Lecturer;
import model.dao.LecturerDAO;
import model.bean.Account;

import java.sql.SQLException;
import java.util.List;

public class LecturerBO {
    // Đối tượng DAO để tương tác với cơ sở dữ liệu
    private LecturerDAO lecturerDAO;

    // Constructor
    public LecturerBO() {
        this.lecturerDAO = new LecturerDAO();
    }

    /**
     * Lấy danh sách tất cả giảng viên
     * @return danh sách giảng viên
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Lecturer> getAllLecturers() throws SQLException, ClassNotFoundException {
        return lecturerDAO.getAllLecturers();
    }

    /**
     * Thêm một giảng viên mới
     * @param lecturer thông tin giảng viên
     * @return true nếu thêm thành công, false nếu thất bại
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean addLecturer(Lecturer lecturer) throws SQLException, ClassNotFoundException {
        // Kiểm tra xem accountID đã được gán hay chưa
        if (lecturerDAO.isAccountAssigned(lecturer.getAccount().getAccountID())) {
            return false; // Trả về false nếu tài khoản đã được gán
        }
        // Nếu chưa, tiến hành thêm giảng viên
        return lecturerDAO.addLecturer(lecturer);
    }

    /**
     * Lấy danh sách các tài khoản chưa được gán cho giảng viên
     * @return danh sách tài khoản chưa được gán
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Account> getUnusedAccounts() throws SQLException, ClassNotFoundException {
        return lecturerDAO.getUnusedAccounts();
    }

    /**
     * Cập nhật thông tin giảng viên
     * @param lecturer thông tin giảng viên cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updateLecturer(Lecturer lecturer) throws SQLException, ClassNotFoundException {
        return lecturerDAO.updateLecturer(lecturer);
    }

    /**
     * Xóa một giảng viên
     * @param lecturerID mã giảng viên cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean deleteLecturer(int lecturerID) throws SQLException, ClassNotFoundException {
        return lecturerDAO.deleteLecturer(lecturerID);
    }

    /**
     * Tìm kiếm thông tin giảng viên theo ID
     * @param lecturerID mã giảng viên cần tìm
     * @return đối tượng Lecturer nếu tìm thấy, null nếu không tìm thấy
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Lecturer getLecturerById(int lecturerID) throws SQLException, ClassNotFoundException {
        return lecturerDAO.getLecturerById(lecturerID);
    }

    /**
     * Kiểm tra giảng viên có sẵn vào thời gian này không
     * @param lecturerID mã giảng viên
     * @param dayOfWeek ngày trong tuần
     * @param startTime thời gian bắt đầu
     * @param endTime thời gian kết thúc
     * @return true nếu giảng viên có sẵn, false nếu giảng viên đã có lịch
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean isLecturerAvailable(int lecturerID, String dayOfWeek, String startTime, String endTime) throws SQLException, ClassNotFoundException {
        return lecturerDAO.isLecturerAvailable(lecturerID, dayOfWeek, startTime, endTime);
    }
}
