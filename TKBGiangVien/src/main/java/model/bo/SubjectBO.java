package model.bo;

import model.bean.Subject;
import model.dao.SubjectDAO;

import java.sql.SQLException;
import java.util.List;

public class SubjectBO {

    private SubjectDAO subjectDAO;

    public SubjectBO() {
        this.subjectDAO = new SubjectDAO();
    }

    // Thêm môn học
    public boolean addSubject(Subject subject) throws SQLException, ClassNotFoundException {
        int result = subjectDAO.insertSubject(subject);
        return result > 0; // Trả về true nếu thêm thành công
    }
    // Cập nhật môn học
    public boolean updateSubject(Subject subject) throws SQLException, ClassNotFoundException {
        return subjectDAO.updateSubject(subject) > 0;
    }

    // Xóa môn học
    public boolean deleteSubject(int subjectID) throws SQLException, ClassNotFoundException {
        return subjectDAO.deleteSubject(subjectID) > 0;
    }

    // Lấy danh sách tất cả môn học
    public List<Subject> getAllSubjects() throws SQLException, ClassNotFoundException {
        return subjectDAO.getAllSubjects();
    }

    // Lấy thông tin môn học theo ID
    public Subject getSubjectById(int subjectID) throws SQLException, ClassNotFoundException {
        return subjectDAO.getSubjectById(subjectID);
    }
}
