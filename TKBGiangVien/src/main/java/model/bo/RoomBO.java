package model.bo;

import model.bean.Room;
import model.dao.RoomDAO;

import java.sql.SQLException;
import java.util.List;

public class RoomBO {

    // Tạo đối tượng RoomDAO để sử dụng các phương thức làm việc với cơ sở dữ liệu
    private RoomDAO roomDAO = new RoomDAO();

    // Thêm phòng học
    public boolean addRoom(Room room) throws SQLException, ClassNotFoundException {
        int result = roomDAO.insertRoom(room);
        return result > 0; // Trả về true nếu thêm thành công
    }

    // Sửa thông tin phòng học
    public boolean updateRoom(Room room) throws SQLException, ClassNotFoundException {
        int result = roomDAO.updateRoom(room);
        return result > 0; // Trả về true nếu cập nhật thành công
    }

    // Xóa phòng học
    public boolean deleteRoom(int roomID) throws SQLException, ClassNotFoundException {
        int result = roomDAO.deleteRoom(roomID);
        return result > 0; // Trả về true nếu xóa thành công
    }

    // Lấy danh sách tất cả phòng học
    public List<Room> getAllRooms() throws SQLException, ClassNotFoundException {
        return roomDAO.getAllRooms();
    }
    
    public List<Room> getRoomsByCapacity(int capacity) throws SQLException, ClassNotFoundException {
        return roomDAO.getRoomsByCapacity(capacity);
    }
    // Lấy thông tin phòng học theo ID
    public Room getRoomById(int roomID) throws SQLException, ClassNotFoundException {
        return roomDAO.getRoomById(roomID); // Gọi phương thức từ DAO
    }
}
