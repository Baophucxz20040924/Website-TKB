package model.bean;

public class Room {
    // Các thuộc tính của lớp Room
    private int roomID;      // Mã phòng học (Primary Key)
    private String roomName; // Tên phòng học
    private int capacity;    // Sức chứa của phòng học

    // Constructor không tham số
    public Room() {
    }

    // Constructor đầy đủ tham số
    public Room(int roomID, String roomName, int capacity) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.capacity = capacity;
    }

    // Getter và Setter cho từng thuộc tính
    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
