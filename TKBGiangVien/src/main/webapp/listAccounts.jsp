<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bo.AccountBO" %>
<%@ page import="model.bean.Account" %>

<%
    AccountBO accountBO = new AccountBO();
    List<Account> accountList = accountBO.getAllAccounts();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Tài Khoản</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhẹ nhàng */
        }

        .container {
            margin-top: 50px;
            background-color: #fff; /* Nền trắng cho nội dung chính */
            padding: 30px;
            border-radius: 10px; /* Bo tròn góc */
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
        }

        h1 {
            text-align: center;
            color: #007bff; /* Màu xanh cho tiêu đề */
            margin-bottom: 30px;
            font-weight: bold;
        }

        .btn {
            padding: 8px 15px;
            font-size: 14px;
        }

        .btn-add {
            background-color: #28a745;
            color: #fff;
        }

        .btn-add:hover {
            background-color: #218838;
            color: #fff;
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

        table {
            margin-top: 20px;
        }

        .table th {
            text-align: center;
            background-color: #007bff; /* Màu xanh cho tiêu đề bảng */
            color: #fff;
        }

        .text-center a {
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Danh Sách Tài Khoản</h1>

        <!-- Hiển thị bảng danh sách tài khoản -->
        <table class="table table-hover table-bordered text-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Đăng Nhập</th>
                    <th>Vai Trò</th>
                    <th>Trạng Thái</th>
                    <th>Hành Động</th>
                </tr>
            </thead>
            <tbody>
                <% if (accountList != null && !accountList.isEmpty()) { %>
                    <% for (Account account : accountList) { %>
                        <tr>
                            <td><%= account.getAccountID() %></td>
                            <td><%= account.getUsername() %></td>
                            <td><%= account.getRole() %></td>
                            <td>
                                <% if (account.isActive()) { %>
                                    <span class="badge bg-success">Hoạt Động</span>
                                <% } else { %>
                                    <span class="badge bg-secondary">Không Hoạt Động</span>
                                <% } %>
                            </td>
                            <td>
                                <!-- Nút Sửa -->
                                <a href="<%= request.getContextPath() %>/editAccount?accountID=<%= account.getAccountID() %>" 
                                   class="btn btn-edit">Sửa</a>
                                <!-- Nút Xóa -->
                                <a href="<%= request.getContextPath() %>/deleteAccount?accountID=<%= account.getAccountID() %>" 
                                   class="btn btn-delete"
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản này không?');">Xóa</a>
                            </td>
                        </tr>
                    <% } %>
                <% } else { %>
                    <!-- Thông báo khi không có dữ liệu -->
                    <tr>
                        <td colspan="5" class="text-center">
                            <div class="alert alert-warning mb-0" role="alert">
                                Không có tài khoản nào trong hệ thống!
                            </div>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Nút Thêm và Quay Lại -->
        <div class="text-center mt-4">
            <a href="<%= request.getContextPath() %>/addAccount" class="btn btn-add">Thêm Tài Khoản Mới</a>
             <a href="<%= request.getContextPath() %>/home" class="btn btn-add">Quay lại</a>

        </div>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
