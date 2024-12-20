<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.Account" %>
<%@ page import="model.bean.Lecturer" %>
<%
    // Lấy thông tin giảng viên từ session
    String lecturerName = (String) session.getAttribute("lecturerName");

    if (lecturerName == null) {
        // Nếu không có tên giảng viên, chuyển hướng về trang login
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ Giảng Viên</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa; /* Màu nền */
        }

        .container {
            margin-top: 100px; /* Tạo khoảng cách phía trên */
            background-color: white; /* Màu nền trắng */
            padding: 40px;
            border-radius: 8px; /* Bo góc */
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng */
        }

        h1 {
            text-align: center;
            color: #007bff; /* Màu xanh dương nhạt */
        }

        .welcome-message {
            text-align: center;
            margin-top: 20px;
            font-size: 18px;
            color: #555; /* Màu chữ xám */
        }

        .btn {
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chào Mừng Giảng Viên!</h1>

        <!-- Hiển thị thông tin giảng viên -->
        <div class="welcome-message">
            <p>Xin chào, <strong><%= lecturerName %></strong>!</p>
            <p>Chào mừng bạn đến với trang chủ của giảng viên.</p>
        </div>

        <!-- Các liên kết đến các chức năng khác cho giảng viên -->
        <div class="text-center">
            <a href="${pageContext.request.contextPath}/login" class="btn btn-danger">Đăng Xuất</a>
            <a href="${pageContext.request.contextPath}/listScheduleLecturer" class="btn btn-danger">Thời Khóa biểu</a>
        </div>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
