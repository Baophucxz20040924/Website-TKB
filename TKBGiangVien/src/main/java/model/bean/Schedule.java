package model.bean;

public class Schedule {
    // Các thuộc tính của lớp Schedule
    private int scheduleID;   // Mã thời khóa biểu (Primary Key)
    private String startTime; // Thời gian bắt đầu
    private String endTime;   // Thời gian kết thúc
    private String dayOfWeek; // Ngày trong tuần
    private int roomID;       // Mã phòng học (Foreign Key)
    private int classID;      // Mã lớp học (Foreign Key)
    private int subjectID;    // Mã môn học (Foreign Key)
    private int lecturerID;   // Mã giảng viên (Foreign Key)

    // Thêm các thuộc tính cho tên môn học, lớp học, và phòng học
    private String subjectName; // Tên môn học
    private String className;   // Tên lớp học
    private String roomName;    // Tên phòng học

    // Constructor không tham số
    public Schedule() {
    }

    // Constructor đầy đủ tham số
    public Schedule(int scheduleID, String startTime, String endTime, String dayOfWeek, 
                    int roomID, int classID, int subjectID, int lecturerID,
                    String subjectName, String className, String roomName) {
        this.scheduleID = scheduleID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.roomID = roomID;
        this.classID = classID;
        this.subjectID = subjectID;
        this.lecturerID = lecturerID;
        this.subjectName = subjectName;
        this.className = className;
        this.roomName = roomName;
    }

    // Getter và Setter cho các thuộc tính
    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    // Getter và Setter cho tên môn học, lớp học, phòng học
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
