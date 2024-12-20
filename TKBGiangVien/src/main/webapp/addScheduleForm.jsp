<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Thêm Thời Khóa Biểu</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 50px;
            max-width: 700px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #007bff;
        }

        .btn-submit {
            background-color: #007bff;
            color: white;
            border: none;
        }

        .btn-submit:hover {
            background-color: #0056b3;
        }

        .btn-cancel {
            background-color: #6c757d;
            color: white;
            border: none;
        }

        .btn-cancel:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Thêm Thời Khóa Biểu</h1>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= message %>
            </div>
        <% } %>

        <!-- Form thêm thời khóa biểu -->
        <form action="<%= request.getContextPath() %>/addSchedule" method="post">
            <!-- Ngày trong tuần -->
            <div class="mb-3">
                <label for="dayOfWeek" class="form-label">Ngày Trong Tuần</label>
                <select id="dayOfWeek" name="dayOfWeek" class="form-select" required>
                    <option value="">-- Chọn Ngày --</option>
                    <option value="Thứ 2">Thứ 2</option>
                    <option value="Thứ 3">Thứ 3</option>
                    <option value="Thứ 4">Thứ 4</option>
                    <option value="Thứ 5">Thứ 5</option>
                    <option value="Thứ 6">Thứ 6</option>
                    <option value="Thứ 7">Thứ 7</option>
                    <option value="Chủ nhật">Chủ nhật</option>
                </select>
            </div>

            <!-- Thời gian bắt đầu -->
            <div class="mb-3">
                <label for="startTime" class="form-label">Thời Gian Bắt Đầu</label>
                <input type="time" id="startTime" name="startTime" class="form-control" required>
            </div>

            <!-- Thời gian kết thúc -->
            <div class="mb-3">
                <label for="endTime" class="form-label">Thời Gian Kết Thúc</label>
                <input type="time" id="endTime" name="endTime" class="form-control" required>
            </div>

            <!-- Mã phòng học -->
            <div class="mb-3">
                <label for="roomID" class="form-label">Mã Phòng Học</label>
                <input type="number" id="roomID" name="roomID" class="form-control" placeholder="Nhập mã phòng học" required>
            </div>

            <!-- Mã lớp học -->
            <div class="mb-3">
                <label for="classID" class="form-label">Mã Lớp Học</label>
                <input type="number" id="classID" name="classID" class="form-control" placeholder="Nhập mã lớp học" required>
            </div>

            <!-- Mã môn học -->
            <div class="mb-3">
                <label for="subjectID" class="form-label">Mã Môn Học</label>
                <input type="number" id="subjectID" name="subjectID" class="form-control" placeholder="Nhập mã môn học" required>
            </div>

            <!-- Mã giảng viên -->
            <div class="mb-3">
                <label for="lecturerID" class="form-label">Mã Giảng Viên</label>
                <input type="number" id="lecturerID" name="lecturerID" class="form-control" placeholder="Nhập mã giảng viên" required>
            </div>

            <!-- Nút hành động -->
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-submit">Thêm Thời Khóa Biểu</button>
                <a href="<%= request.getContextPath() %>/listSchedules" class="btn btn-cancel">Quay Lại</a>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
