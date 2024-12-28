<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Room" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Phòng Học</title>
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

        .btn-add {
            margin-bottom: 20px;
            background-color: #28a745;
            color: white;
        }

        .btn-add:hover {
            background-color: #218838;
            color: white;
        }

        .btn-search {
            margin-left: 10px;
        }

        table {
            margin-top: 20px;
        }

        .btn-edit {
            background-color: #ffc107;
            color: white;
        }

        .btn-edit:hover {
            background-color: #e0a800;
        }

        .btn-delete {
            background-color: #dc3545;
            color: white;
        }

        .btn-delete:hover {
            background-color: #c82333;
        }

        .text-center {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Danh Sách Phòng Học</h1>

        <!-- Nút Thêm Phòng Học -->
        <div class="text-end">
            <a href="${pageContext.request.contextPath}/addRoom" class="btn btn-add">Thêm Phòng Học</a>
        </div>

        <!-- Form Tìm Kiếm Phòng Học Theo Sức Chứa -->
        <form action="${pageContext.request.contextPath}/listRooms" method="get" class="mb-3 d-flex align-items-center">
            <input type="number" class="form-control" id="capacity" name="capacity" 
                   placeholder="Nhập sức chứa tối thiểu" required>
            <button type="submit" class="btn btn-primary btn-search">Tìm Kiếm</button>
        </form>

        <!-- Kiểm tra danh sách phòng học -->
        <%
            List<Room> roomList = (List<Room>) request.getAttribute("roomList");
            String filterMessage = (String) request.getAttribute("filterMessage");
        %>
        
        <% if (filterMessage != null) { %>
            <div class="alert alert-info text-center" role="alert">
                <%= filterMessage %>
            </div>
        <% } %>

        <% if (roomList != null && !roomList.isEmpty()) { %>
        <!-- Bảng danh sách phòng học -->
        <table class="table table-bordered table-hover text-center">
            <thead class="table-primary">
                <tr>
                    <th>Mã Phòng</th>
                    <th>Tên Phòng</th>
                    <th>Sức Chứa</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <% for (Room room : roomList) { %>
                <tr>
                    <td><%= room.getRoomID() %></td>
                    <td><%= room.getRoomName() %></td>
                    <td><%= room.getCapacity() %></td>
                    <td>
                        <!-- Nút Sửa -->
                        <a href="<%= request.getContextPath() %>/editRoom?roomID=<%= room.getRoomID() %>" 
                           class="btn btn-edit">Sửa</a>
                        <!-- Nút Xóa -->
                        <a href="<%= request.getContextPath() %>/deleteRoom?roomID=<%= room.getRoomID() %>" 
                           class="btn btn-delete"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa phòng học này không?');">Xóa</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } else { %>
        <!-- Thông báo khi không có dữ liệu -->
        <div class="alert alert-warning text-center" role="alert">
            Không có phòng học nào trong hệ thống!
        </div>
        <% } %>

        <!-- Nút quay lại trang chủ -->
        <div class="text-center">
            <a href="<%= request.getContextPath() %>/home" class="btn btn-add">Quay lại</a>
        </div>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
