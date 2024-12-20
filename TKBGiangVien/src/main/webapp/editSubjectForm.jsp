<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.Subject" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Môn Học</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhạt */
        }

        .container {
            margin-top: 50px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
        }

        h1 {
            text-align: center;
            color: #007bff; /* Màu xanh */
            margin-bottom: 30px;
            font-weight: bold;
        }

        label {
            font-weight: bold;
        }

        .btn {
            padding: 10px 20px;
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

        .alert {
            text-align: center;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chỉnh Sửa Môn Học</h1>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null && !message.isEmpty()) { 
        %>
            <div class="alert alert-danger"><%= message %></div>
        <% } %>

        <!-- Lấy thông tin môn học từ request attribute -->
        <% 
            Subject subject = (Subject) request.getAttribute("subject");
            if (subject == null) {
        %>
            <div class="alert alert-warning">Không tìm thấy thông tin môn học!</div>
        <% 
            } else { 
        %>

        <!-- Form chỉnh sửa môn học -->
        <form action="<%= request.getContextPath() %>/editSubject" method="post">
            <!-- Truyền SubjectID dưới dạng hidden -->
            <input type="hidden" name="subjectID" value="<%= subject.getSubjectID() %>">

            <!-- Tên Môn Học -->
            <div class="mb-3">
                <label for="subjectName" class="form-label">Tên Môn Học</label>
                <input type="text" class="form-control" id="subjectName" name="subjectName" 
                       value="<%= subject.getSubjectName() %>" placeholder="Nhập tên môn học" required>
            </div>

            <!-- Số Tín Chỉ -->
            <div class="mb-3">
                <label for="credits" class="form-label">Số Tín Chỉ</label>
                <input type="number" class="form-control" id="credits" name="credits" 
                       value="<%= subject.getCredits() %>" placeholder="Nhập số tín chỉ" min="1" required>
            </div>

            <!-- Nút Lưu và Quay Lại -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Lưu</button>
                <a href="<%= request.getContextPath() %>/listSubjects" class="btn btn-secondary">Quay Lại</a>
            </div>
        </form>
        <% } %>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
