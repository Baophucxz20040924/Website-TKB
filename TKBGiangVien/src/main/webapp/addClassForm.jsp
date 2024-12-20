<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thêm Lớp Học</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Màu nền nhạt */
            font-family: Arial, sans-serif;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        .form-title {
            text-align: center;
            margin-bottom: 20px;
            color: #007bff; /* Màu xanh tiêu đề */
        }
        .btn-submit {
            background-color: #007bff;
            color: white;
        }
        .btn-submit:hover {
            background-color: #0056b3;
        }
        .btn-back {
            background-color: #6c757d;
            color: white;
        }
        .btn-back:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2 class="form-title">Thêm Lớp Học</h2>

            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-danger" role="alert">
                    ${message}
                </div>
            </c:if>

            <!-- Form thêm lớp học -->
            <form action="${pageContext.request.contextPath}/addClass" method="post">
                <!-- Tên lớp học -->
                <div class="mb-3">
                    <label for="className" class="form-label">Tên Lớp Học</label>
                    <input type="text" id="className" name="className" class="form-control" placeholder="Nhập tên lớp học" required>
                </div>

                <!-- Số lượng sinh viên -->
                <div class="mb-3">
                    <label for="numberOfStudents" class="form-label">Số Lượng Sinh Viên</label>
                    <input type="number" id="numberOfStudents" name="numberOfStudents" class="form-control" placeholder="Nhập số lượng sinh viên" min="1" required>
                </div>

                <!-- Nút gửi và quay lại -->
<div class="d-flex justify-content-between">
        <a href="<%= request.getContextPath() %>/listClasses" class="btn btn-secondary">Quay Lại</a>
    <button type="submit" class="btn btn-submit">Thêm Lớp</button>
</div>

            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
