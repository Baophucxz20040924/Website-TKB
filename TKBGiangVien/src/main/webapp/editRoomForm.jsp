<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.Room" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Phòng Học</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #007bff;
            margin-bottom: 30px;
        }
        .alert {
            text-align: center;
        }
        .btn {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chỉnh Sửa Phòng Học</h1>

        <!-- Kiểm tra và hiển thị thông báo lỗi nếu có -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <div class="alert alert-warning" role="alert">
            <%= message %>
        </div>
        <%
            }
        %>

        <!-- Form chỉnh sửa phòng học -->
        <%
            Room room = (Room) request.getAttribute("room");
            if (room != null) { 
        %>
        <form action="<%= request.getContextPath() %>/editRoom" method="post">
            <!-- Mã phòng học (ẩn) -->
            <input type="hidden" name="roomID" value="<%= room.getRoomID() %>">

            <!-- Tên phòng học -->
            <div class="mb-3">
                <label for="roomName" class="form-label">Tên Phòng Học</label>
                <input type="text" class="form-control" id="roomName" name="roomName" 
                       value="<%= room.getRoomName() %>" required>
            </div>

            <!-- Sức chứa -->
            <div class="mb-3">
                <label for="capacity" class="form-label">Sức Chứa</label>
                <input type="number" class="form-control" id="capacity" name="capacity" 
                       value="<%= room.getCapacity() %>" min="1" required>
            </div>

            <!-- Nút lưu và quay lại -->
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
                <a href="<%= request.getContextPath() %>/listRooms" class="btn btn-secondary">Quay Lại</a>
            </div>
        </form>
        <%
            } else { 
        %>
        <div class="alert alert-warning" role="alert">
            Không tìm thấy thông tin phòng học!
        </div>
        <%
            }
        %>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
