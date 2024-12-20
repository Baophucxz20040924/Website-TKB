<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa tài khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            width: 50%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="checkbox"] {
            margin-right: 10px;
        }
        .btn {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .btn-cancel {
            background-color: #f44336;
        }
        .btn-cancel:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <h1 style="text-align: center;">Chỉnh sửa Tài Khoản</h1>
    
    <form action="editAccount" method="post">
        <!-- Truyền AccountID dưới dạng hidden -->
        <input type="hidden" name="accountID" value="${account.accountID}">

        <!-- Tên đăng nhập -->
        <label for="username">Tên đăng nhập:</label>
        <input type="text" id="username" name="username" value="${account.username}" required>

        <!-- Mật khẩu -->
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" value="${account.password}" required>

        <!-- Vai trò -->
        <label for="role">Vai trò:</label>
        <select id="role" name="role" required>
            <option value="Admin" ${account.role == "Admin" ? "selected" : ""}>Admin</option>
            <option value="Lecturer" ${account.role == "Lecturer" ? "selected" : ""}>Lecturer</option>
        </select>

        <!-- Trạng thái -->
       

        <!-- Nút Lưu và Hủy -->
        <button type="submit" class="btn">Lưu</button>
        <a href="listAccounts" class="btn btn-cancel">Hủy</a>
    </form>
</body>
</html>