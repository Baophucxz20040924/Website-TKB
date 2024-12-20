package model.bo;

import model.bean.Class;
import model.dao.ClassDAO;

import java.sql.SQLException;
import java.util.List;

public class ClassBO {

    // Tạo đối tượng ClassDAO để sử dụng các phương thức làm việc với cơ sở dữ liệu
    private ClassDAO classDAO = new ClassDAO();

    // Thêm lớp học
    public boolean addClass(Class cls) throws SQLException, ClassNotFoundException {
        int result = classDAO.insertClass(cls);
        return result > 0; // Trả về true nếu thêm thành công
    }

    // Sửa lớp học
    public boolean updateClass(Class cls) throws SQLException, ClassNotFoundException {
        int result = classDAO.updateClass(cls);
        return result > 0; // Trả về true nếu sửa thành công
    }

    // Xóa lớp học
    public boolean deleteClass(int classID) throws SQLException, ClassNotFoundException {
        int result = classDAO.deleteClass(classID);
        return result > 0; // Trả về true nếu xóa thành công
    }

    // Lấy danh sách tất cả lớp học
    public List<Class> getAllClasses() throws SQLException, ClassNotFoundException {
        return classDAO.getAllClasses();
    }

    // Lấy thông tin lớp học theo ID
    public Class getClassById(int classID) throws SQLException, ClassNotFoundException {
        return classDAO.getClassById(classID);
    }
}
