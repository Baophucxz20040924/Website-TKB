<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.Class" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa Lớp Học</title>
    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhạt */
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 50px;
            max-width: 600px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #007bff;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
        }

        .form-control {
            border-radius: 5px;
            margin-bottom: 15px;
        }

        .btn {
            padding: 10px 20px;
            border-radius: 5px;
        }

        .btn-primary {
            background-color: #007bff;
            color: #ffffff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
            color: #ffffff;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chỉnh sửa Lớp Học</h1>
        <form action="${pageContext.request.contextPath}/editClass" method="post">
            <!-- Hidden input để truyền ClassID -->
            <input type="hidden" name="classID" value="${clazz.classID}">

            <!-- Tên Lớp -->
            <div class="mb-3">
                <label for="className">Tên Lớp:</label>
                <input type="text" class="form-control" id="className" name="className" 
                       value="${clazz.className}" required>
            </div>

            <!-- Số lượng sinh viên -->
            <div class="mb-3">
                <label for="numberOfStudents">Số Lượng Sinh Viên:</label>
                <input type="number" class="form-control" id="numberOfStudents" 
                       name="numberOfStudents" value="${clazz.numberOfStudents}" required>
            </div>

            <!-- Nút Lưu và Quay Lại -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
                <a href="${pageContext.request.contextPath}/listClasses" class="btn btn-secondary">Quay Lại</a>
            </div>
        </form>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
