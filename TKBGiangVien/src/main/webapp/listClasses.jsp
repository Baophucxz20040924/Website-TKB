<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bo.ClassBO" %>
<%@ page import="model.bean.Class" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Lớp Học</title>

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

        .btn {
            padding: 8px 12px;
            font-size: 14px;
        }

        .btn-add {
            background-color: #28a745;
            color: white;
            margin-right: 10px;
        }

        .btn-add:hover {
            background-color: #218838;
            color: white;
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Danh Sách Lớp Học</h1>

        <!-- Hiển thị bảng danh sách lớp học -->
        <table class="table table-hover table-bordered text-center">
            <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Tên Lớp</th>
                    <th>Số Lượng Sinh Viên</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ClassBO classBO = new ClassBO();
                    List<Class> classList = classBO.getAllClasses();
                    
                    if (classList != null && !classList.isEmpty()) {
                        for (Class cls : classList) { 
                %>
                <tr>
                    <td><%= cls.getClassID() %></td>
                    <td><%= cls.getClassName() %></td>
                    <td><%= cls.getNumberOfStudents() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/editClass?classID=<%= cls.getClassID() %>" 
                           class="btn btn-edit">Sửa</a>
                        <a href="<%= request.getContextPath() %>/deleteClass?classID=<%= cls.getClassID() %>" 
                           class="btn btn-delete" 
                           onclick="return confirm('Bạn có chắc chắn muốn xóa lớp học này?');">Xóa</a>
                    </td>
                </tr>
                <% 
                        } 
                    } else { 
                %>
                <!-- Hiển thị thông báo khi không có lớp học -->
                <tr>
                    <td colspan="4" class="text-center">
                        <div class="alert alert-warning mb-0" role="alert">
                            Không có lớp học nào trong hệ thống!
                        </div>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Nút thêm lớp học và quay lại -->
        <div class="text-center mt-4">
            <a href="<%= request.getContextPath() %>/addClass" class="btn btn-add">Thêm Lớp Học Mới</a>
             <a href="<%= request.getContextPath() %>/home" class="btn btn-add">Quay lại</a>

        </div>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
