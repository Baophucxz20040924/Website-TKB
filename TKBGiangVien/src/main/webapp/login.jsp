<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhạt */
        }

        .form-container {
            max-width: 400px; /* Giới hạn chiều rộng của form */
            margin: 100px auto; /* Căn giữa */
            padding: 30px;
            border-radius: 8px; /* Bo góc */
            background-color: white; /* Màu nền form */
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
        }

        .form-title {
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #007bff; /* Màu xanh của tiêu đề */
        }

        .form-group label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .btn-submit {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }

        .btn-submit:hover {
            background-color: #0056b3;
            color: #fff;
        }

        .alert-danger {
            text-align: center;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1 class="form-title">Đăng Nhập</h1>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <c:if test="${not empty message}">
            <div class="alert alert-danger" role="alert">
                ${message}
            </div>
        </c:if>

        <!-- Form đăng nhập -->
        <form action="${pageContext.request.contextPath}/login" method="post">
            <!-- Tên đăng nhập -->
            <div class="form-group mb-3">
                <label for="username">Tên Đăng Nhập:</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Nhập tên đăng nhập" required>
            </div>

            <!-- Mật khẩu -->
            <div class="form-group mb-3">
                <label for="password">Mật Khẩu:</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
            </div>

            <!-- Nút đăng nhập -->
            <button type="submit" class="btn btn-submit btn-block w-100">Đăng Nhập</button>
        </form>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
