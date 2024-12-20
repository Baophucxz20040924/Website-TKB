<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Account" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thêm Giảng Viên</title>
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
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
        }
        h1 {
            text-align: center;
            color: #007bff;
            font-weight: bold;
            margin-bottom: 30px;
        }
        .form-control, .form-select {
            margin-bottom: 20px;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        .message {
            color: red;
            font-weight: bold;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thêm Giảng Viên</h1>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null && !message.isEmpty()) { 
        %>
            <div class="alert alert-danger" role="alert">
                <%= message %>
            </div>
        <% } %>

        <!-- Form thêm giảng viên -->
        <form action="<%= request.getContextPath() %>/addLecturer" method="post">
            <!-- Tên giảng viên -->
            <div class="mb-3">
                <label for="lecturerName" class="form-label">Tên Giảng Viên</label>
                <input type="text" class="form-control" id="lecturerName" name="name" placeholder="Nhập tên giảng viên" required>
            </div>

            <!-- Email -->
            <div class="mb-3">
                <label for="lecturerEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="lecturerEmail" name="email" placeholder="Nhập email giảng viên" required>
            </div>

            <!-- Tài khoản -->
            <div class="mb-3">
                <label for="accountID" class="form-label">Tài Khoản</label>
                <select name="accountID" id="accountID" class="form-select" required>
                    <option value="">-- Chọn Tài Khoản --</option>
                    <% 
                        List<Account> accountList = (List<Account>) request.getAttribute("accountList");
                        if (accountList != null) {
                            for (Account account : accountList) { 
                    %>
                        <option value="<%= account.getAccountID() %>"><%= account.getUsername() %></option>
                    <% 
                            }
                        } 
                    %>
                </select>
            </div>

            <!-- Nút hành động -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Thêm Giảng Viên</button>
                <a href="<%= request.getContextPath() %>/listLecturers" class="btn btn-secondary">Quay Lại</a>
            </div>
        </form>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
