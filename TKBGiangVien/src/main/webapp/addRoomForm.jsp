<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thêm Phòng Học</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhạt */
        }

        .container {
            margin-top: 80px; /* Tạo khoảng cách phía trên */
            background-color: white; /* Nền trắng */
            padding: 30px;
            border-radius: 12px; /* Bo tròn góc */
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
            max-width: 600px;
        }

        h1 {
            color: #007bff; /* Màu xanh dương */
            text-align: center;
            margin-bottom: 20px;
        }

        .btn-submit {
            background-color: #28a745; /* Màu xanh lá */
            color: white;
            border: none;
        }

        .btn-submit:hover {
            background-color: #218838;
        }

        .btn-cancel {
            background-color: #dc3545; /* Màu đỏ */
            color: white;
            border: none;
        }

        .btn-cancel:hover {
            background-color: #c82333;
        }

        .form-label {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thêm Phòng Học</h1>

        <!-- Form thêm phòng học -->
 <form action="<%= request.getContextPath() %>/addRoom" method="post">
            <!-- Tên phòng -->
               <div class="mb-3">
                <label for="roomName" class="form-label">Tên Phòng</label>
                <input type="text" class="form-control" id="roomName" name="roomName" placeholder="Nhập tên phòng" required>
            </div>
            <!-- Sức chứa -->
            <div class="mb-3">
                <label for="capacity" class="form-label">Sức Chứa</label>
                <input type="number" class="form-control" id="capacity" name="capacity" placeholder="Nhập sức chứa" min="1" required>
            </div>

            <!-- Nút submit và hủy -->
            <div class="text-center">
                <button type="submit" class="btn btn-submit">Lưu</button>
                <a href="${pageContext.request.contextPath}/listRooms" class="btn btn-cancel">Quay lại</a>
            </div>
        </form>

    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
