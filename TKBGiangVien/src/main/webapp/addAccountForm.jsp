<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thêm Tài Khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 400px;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"],
        input[type="password"],
        select {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        input[type="checkbox"] {
            margin-bottom: 15px;
        }
        button {
            padding: 10px 15px;
            font-size: 16px;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #45a049;
        }
        .message {
            text-align: center;
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thêm Tài Khoản</h1>

        <!-- Hiển thị thông báo nếu có -->
        <c:if test="${not empty message}">
            <p class="message">${message}</p>
        </c:if>

        <!-- Form thêm tài khoản -->
        <form action="${pageContext.request.contextPath}/addAccount" method="post">
            <label for="username">Tên Đăng Nhập:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Mật Khẩu:</label>
            <input type="password" id="password" name="password" required>

            <label for="role">Vai Trò:</label>
            <select id="role" name="role" required>
                <option value="Lecturer">Lecturer</option>
                <option value="Admin">Admin</option>
            </select>

            <label for="isActive">Kích Hoạt:</label>
            <input type="checkbox" id="isActive" name="isActive">

            <button type="submit">Thêm Tài Khoản</button>
                <a href="<%= request.getContextPath() %>/listAccounts" class="btn btn-secondary">Quay Lại</a>
        </form>
    </div>
</body>
</html>
