<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bo.ScheduleBO" %>
<%@ page import="model.bean.Schedule" %>
<%@ page import="model.bo.LecturerBO" %>
<%@ page import="model.bean.Lecturer" %>

<%
    // Lấy danh sách thời khóa biểu từ ScheduleBO
    ScheduleBO scheduleBO = new ScheduleBO();
    List<Schedule> scheduleList = scheduleBO.getAllSchedules();

    // Lấy danh sách giảng viên từ LecturerBO để hiển thị tên giảng viên
    LecturerBO lecturerBO = new LecturerBO();
    List<Lecturer> lecturerList = lecturerBO.getAllLecturers();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Thời Khóa Biểu</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhẹ nhàng */
        }

        .container {
            margin-top: 50px;
            background-color: #fff; /* Nền trắng cho nội dung chính */
            padding: 30px;
            border-radius: 10px; /* Bo tròn góc */
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
        }

        h1 {
            text-align: center;
            color: #007bff; /* Màu xanh cho tiêu đề */
            margin-bottom: 30px;
            font-weight: bold;
        }

        .btn {
            padding: 8px 15px;
            font-size: 14px;
        }

        .btn-add {
            background-color: #28a745;
            color: #fff;
        }

        .btn-add:hover {
            background-color: #218838;
            color: #fff;
        }

        .btn-edit {
            background-color: #ffc107;
            color: white;
        }

        .btn-edit:hover {
            background-color: #e0a800;
            color: white;
        }

        .btn-delete {
            background-color: #dc3545;
            color: white;
        }

        .btn-delete:hover {
            background-color: #c82333;
            color: white;
        }

        table {
            margin-top: 20px;
        }

        .table th {
            text-align: center;
            background-color: #007bff; /* Màu xanh cho tiêu đề bảng */
            color: #fff;
        }

        .text-center a {
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Danh Sách Thời Khóa Biểu</h1>

        <!-- Hiển thị bảng danh sách thời khóa biểu -->
        <table class="table table-hover table-bordered text-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Môn Học</th>
                    <th>Lớp Học</th>
                    <th>Phòng Học</th>
                    <th>Ngày</th>
                    <th>Thời Gian</th>
                    <th>Giảng Viên</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <% if (scheduleList != null && !scheduleList.isEmpty()) { %>
                    <% for (Schedule schedule : scheduleList) { 
                        // Tìm giảng viên theo lecturerID
                        Lecturer lecturer = null;
                        for (Lecturer lec : lecturerList) {
                            if (lec.getLecturerID() == schedule.getLecturerID()) {
                                lecturer = lec;
                                break;
                            }
                        }
                    %>
                        <tr>
                            <td><%= schedule.getScheduleID() %></td>
                            <td><%= schedule.getSubjectName() %></td>
                            <td><%= schedule.getClassName() %></td>
                            <td><%= schedule.getRoomName() %></td>
                            <td><%= schedule.getDayOfWeek() %></td>
                            <td><%= schedule.getStartTime() + " - " + schedule.getEndTime() %></td>
                            <td><%= lecturer != null ? lecturer.getName() : "Không có giảng viên" %></td>
                            <td>
                                <!-- Nút Sửa -->
                                <a href="<%= request.getContextPath() %>/editSchedule?scheduleID=<%= schedule.getScheduleID() %>" 
                                   class="btn btn-edit">Sửa</a>
                                <!-- Nút Xóa -->
                                <a href="<%= request.getContextPath() %>/deleteSchedule?scheduleID=<%= schedule.getScheduleID() %>" 
                                   class="btn btn-delete"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa thời khóa biểu này không?');">Xóa</a>
                            </td>
                        </tr>
                    <% } %>
                <% } else { %>
                    <!-- Thông báo khi không có dữ liệu -->
                    <tr>
                        <td colspan="8" class="text-center">
                            <div class="alert alert-warning mb-0" role="alert">
                                Không có thời khóa biểu nào trong hệ thống!
                            </div>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Nút Thêm và Quay Lại -->
        <div class="text-center mt-4">
            <a href="<%= request.getContextPath() %>/addSchedule" class="btn btn-add">Thêm Thời Khóa Biểu Mới</a>
            <a href="<%= request.getContextPath() %>/home" class="btn btn-add">Quay lại</a>
        </div>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
