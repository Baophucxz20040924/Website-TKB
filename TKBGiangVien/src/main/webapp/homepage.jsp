<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.Account" %>
<%
    Account account = (Account) session.getAttribute("account");

    // Kiểm tra lại vai trò lần cuối (phòng trường hợp lỗi từ Servlet)
    if (account == null || !"Admin".equals(account.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ</title>

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

        .action-links {
            text-align: center;
            margin-top: 20px;
        }

        .btn {
            margin: 5px;
        }

        .welcome-message {
            text-align: center;
            margin-top: 20px;
            font-size: 18px;
            color: #555; /* Màu chữ xám */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chào Mừng Bạn Đến Với Trang Chủ!</h1>

        <!-- Kiểm tra xem người dùng đã đăng nhập hay chưa -->
        <c:choose>
            <c:when test="${not empty sessionScope.account}">
                <div class="welcome-message">
                    <p>Xin chào, <strong>${sessionScope.account.username}</strong>!</p>
                    <p class="lead">Bạn đang truy cập với vai trò <strong>Admin</strong>.</p>
                </div>
                <div class="action-links">
                    <a href="${pageContext.request.contextPath}/listAccounts" class="btn btn-danger">Danh Sách Tài Khoản</a>
                    <a href="${pageContext.request.contextPath}/listClasses" class="btn btn-danger">Danh Sách Lớp Học</a>
                    <a href="${pageContext.request.contextPath}/listLecturers" class="btn btn-danger">Danh Sách Giảng Viên</a>
                    <a href="${pageContext.request.contextPath}/listRooms" class="btn btn-danger">Danh Sách Phòng Học</a>
                    <a href="${pageContext.request.contextPath}/listSubjects" class="btn btn-danger">Danh Sách Môn Học</a>
                    <a href="${pageContext.request.contextPath}/listSchedules" class="btn btn-danger">Danh Sách Thời Khóa Biểu</a>
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-danger">Đăng Xuất</a>
                </div>
            </c:when>

        </c:choose>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
