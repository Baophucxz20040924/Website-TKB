package model.dao;

import model.bean.Room;
import model.dao.ConnectDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // Thêm phòng học
	   
	    public int insertRoom(Room room) throws SQLException, ClassNotFoundException {
	        String sql = "INSERT INTO Room (RoomName, Capacity) VALUES (?, ?)";
	        try (Connection connection = ConnectDatabase.getMySQLConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setString(1, room.getRoomName());
	            statement.setInt(2, room.getCapacity());

	            return statement.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	        }
	    }
	

    // Cập nhật thông tin phòng học
    public int updateRoom(Room room) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Room SET RoomName = ?, Capacity = ? WHERE RoomID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, room.getRoomName());
            statement.setInt(2, room.getCapacity());
            statement.setInt(3, room.getRoomID());

            return statement.executeUpdate();
        }
    }

    // Xóa phòng học
    public int deleteRoom(int roomID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Room WHERE RoomID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, roomID);

            return statement.executeUpdate();
        }
    }

    // Lấy danh sách tất cả phòng học
    public List<Room> getAllRooms() throws SQLException, ClassNotFoundException {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT RoomID, RoomName, Capacity FROM Room";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomID(resultSet.getInt("RoomID"));
                room.setRoomName(resultSet.getString("RoomName"));
                room.setCapacity(resultSet.getInt("Capacity"));

                roomList.add(room);
            }
        }
        return roomList;
    }

    public Room getRoomById(int roomID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT RoomID, RoomName, Capacity FROM Room WHERE RoomID = ?";
        try (Connection connection = ConnectDatabase.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Room room = new Room();
                    room.setRoomID(resultSet.getInt("RoomID"));
                    room.setRoomName(resultSet.getString("RoomName"));
                    room.setCapacity(resultSet.getInt("Capacity"));
                    return room;
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy phòng
    }
}
