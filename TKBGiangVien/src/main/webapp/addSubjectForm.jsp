<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thêm Môn Học</title>
    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhẹ nhàng */
        }

        .container {
            max-width: 600px;
            margin-top: 50px;
            padding: 30px;
            background-color: #ffffff; /* Nền trắng cho form */
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #007bff; /* Màu xanh Bootstrap */
            margin-bottom: 30px;
        }

        .form-label {
            font-weight: bold;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            width: 48%;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
            width: 48%;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }

        .message {
            text-align: center;
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thêm Môn Học</h1>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null && !message.isEmpty()) { 
        %>
            <div class="alert alert-danger text-center"><%= message %></div>
        <% } %>

        <!-- Form thêm môn học -->
        <form action="<%= request.getContextPath() %>/addSubject" method="post">
            <!-- Tên Môn Học -->
            <div class="mb-3">
                <label for="subjectName" class="form-label">Tên Môn Học</label>
                <input type="text" class="form-control" id="subjectName" name="subjectName" 
                       placeholder="Nhập tên môn học" required>
            </div>

            <!-- Số Tín Chỉ -->
            <div class="mb-3">
                <label for="credits" class="form-label">Số Tín Chỉ</label>
                <input type="number" class="form-control" id="credits" name="credits" 
                       placeholder="Nhập số tín chỉ" min="1" required>
            </div>

            <!-- Nút hành động -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Thêm Môn Học</button>
                <a href="<%= request.getContextPath() %>/listSubjects" class="btn btn-secondary">Quay Lại</a>
            </div>
        </form>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
