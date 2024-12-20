<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Subject" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Môn Học</title>
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
            margin-bottom: 30px;
            color: #007bff;
        }

        table {
            margin-top: 20px;
        }

        .btn {
            padding: 5px 10px;
            font-size: 14px;
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

        .btn-add {
            margin-top: 20px;
            background-color: #28a745;
            color: white;
        }

        .btn-add:hover {
            background-color: #218838;
        }

        .message {
            text-align: center;
            font-size: 18px;
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Danh Sách Môn Học</h1>

        <!-- Hiển thị thông báo nếu có -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null && !message.isEmpty()) {
        %>
        <div class="alert alert-danger text-center" role="alert">
            <%= message %>
        </div>
        <% } %>

        <!-- Bảng hiển thị danh sách môn học -->
        <table class="table table-bordered table-hover text-center">
            <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Tên Môn Học</th>
                    <th>Số Tín Chỉ</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Subject> subjectList = (List<Subject>) request.getAttribute("subjectList");
                    if (subjectList != null && !subjectList.isEmpty()) {
                        for (Subject subject : subjectList) {
                %>
                <tr>
                    <td><%= subject.getSubjectID() %></td>
                    <td><%= subject.getSubjectName() %></td>
                    <td><%= subject.getCredits() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/editSubject?subjectID=<%= subject.getSubjectID() %>" 
                           class="btn btn-edit btn-sm">Sửa</a>
                        <a href="<%= request.getContextPath() %>/deleteSubject?subjectID=<%= subject.getSubjectID() %>" 
                           class="btn btn-delete btn-sm"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa môn học này?');">Xóa</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">Không có môn học nào trong hệ thống.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Nút thêm môn học -->
        <div class="text-center mt-4">
            <a href="<%= request.getContextPath() %>/addSubject" class="btn btn-add">Thêm Môn Học Mới</a>
             <a href="<%= request.getContextPath() %>/home" class="btn btn-add">Quay lại</a>

        </div>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
