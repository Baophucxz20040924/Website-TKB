<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Lecturer" %>
<%@ page import="model.bean.Account" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Giảng Viên</title>
    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Nền nhẹ nhàng */
        }
        .container {
            margin-top: 50px;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #007bff;
            font-weight: bold;
            margin-bottom: 30px;
        }
        table {
            margin-top: 20px;
        }
        .btn-add {
            background-color: #28a745;
            color: #fff;
        }
        .btn-add:hover {
            background-color: #218838;
            color: #fff;
        }
        .btn-warning {
            background-color: #ffc107;
            color: white;
        }
        .btn-warning:hover {
            background-color: #e0a800;
            color: white;
        }
        .btn-danger {
            background-color: #dc3545;
            color: white;
        }
        .btn-danger:hover {
            background-color: #c82333;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Tiêu đề trang -->
        <h1>Danh Sách Giảng Viên</h1>

        <!-- Bảng hiển thị danh sách giảng viên -->
        <table class="table table-bordered table-hover text-center">
            <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Tên Giảng Viên</th>
                    <th>Email</th>
                    <th>Tài Khoản</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Lấy danh sách giảng viên từ request attribute
                    List<Lecturer> lecturerList = (List<Lecturer>) request.getAttribute("lecturerList");
                    if (lecturerList != null && !lecturerList.isEmpty()) {
                        for (Lecturer lecturer : lecturerList) {
                %>
                <tr>
                    <td><%= lecturer.getLecturerID() %></td>
                    <td><%= lecturer.getName() %></td>
                    <td><%= lecturer.getEmail() %></td>
                    <td>
                        <% 
                            Account account = lecturer.getAccount();
                            if (account != null) { 
                                out.print(account.getUsername());
                            } else {
                                out.print("Không có tài khoản");
                            }
                        %>
                    </td>
                    <td>
                        <a href="<%= request.getContextPath() %>/editLecturer?lecturerID=<%= lecturer.getLecturerID() %>" 
                           class="btn btn-warning btn-sm">Sửa</a>
                        <a href="<%= request.getContextPath() %>/deleteLecturer?lecturerID=<%= lecturer.getLecturerID() %>" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa giảng viên này?');">Xóa</a>
                    </td>
                </tr>
                <%
                        } 
                    } else {
                %>
                <!-- Hiển thị thông báo khi không có dữ liệu -->
                <tr>
                    <td colspan="5" class="text-center">
                        <div class="alert alert-warning mb-0">Không có giảng viên nào trong hệ thống!</div>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Nút thêm giảng viên và quay lại -->
        <div class="text-center mt-4">
            <a href="<%= request.getContextPath() %>/addLecturer" class="btn btn-add btn-lg">Thêm Giảng Viên</a>
          <a href="<%= request.getContextPath() %>/home" class="btn btn-add">Quay lại</a>

        </div>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
