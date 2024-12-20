<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Thời Khóa Biểu</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa; /* Màu nền */
        }

        .container {
            margin-top: 50px;
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #007bff;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .btn {
            padding: 10px 20px;
            font-size: 16px;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chỉnh Sửa Thời Khóa Biểu</h1>

        <!-- Hiển thị thông báo lỗi (nếu có) -->
        <c:if test="${not empty message}">
            <div class="alert alert-danger">${message}</div>
        </c:if>

        <!-- Form chỉnh sửa thời khóa biểu -->
        <form action="${pageContext.request.contextPath}/editSchedule" method="post">
            <!-- Truyền ScheduleID dưới dạng hidden -->
            <input type="hidden" name="scheduleID" value="${schedule.scheduleID}">

            <!-- Ngày trong tuần -->
            <div class="form-group">
                <label for="dayOfWeek">Ngày trong tuần:</label>
                <select class="form-select" id="dayOfWeek" name="dayOfWeek" required>
                    <option value="Thứ 2" ${schedule.dayOfWeek == 'Thứ 2' ? 'selected' : ''}>Thứ 2</option>
                    <option value="Thứ 3" ${schedule.dayOfWeek == 'Thứ 3' ? 'selected' : ''}>Thứ 3</option>
                    <option value="Thứ 4" ${schedule.dayOfWeek == 'Thứ 4' ? 'selected' : ''}>Thứ 4</option>
                    <option value="Thứ 5" ${schedule.dayOfWeek == 'Thứ 5' ? 'selected' : ''}>Thứ 5</option>
                    <option value="Thứ 6" ${schedule.dayOfWeek == 'Thứ 6' ? 'selected' : ''}>Thứ 6</option>
                    <option value="Thứ 7" ${schedule.dayOfWeek == 'Thứ 7' ? 'selected' : ''}>Thứ 7</option>
                    <option value="Chủ nhật" ${schedule.dayOfWeek == 'Chủ nhật' ? 'selected' : ''}>Chủ nhật</option>
                </select>
            </div>

            <!-- Thời gian bắt đầu -->
            <div class="form-group">
                <label for="startTime">Thời gian bắt đầu:</label>
                <input type="time" class="form-control" id="startTime" name="startTime" value="${schedule.startTime}" required>
            </div>

            <!-- Thời gian kết thúc -->
            <div class="form-group">
                <label for="endTime">Thời gian kết thúc:</label>
                <input type="time" class="form-control" id="endTime" name="endTime" value="${schedule.endTime}" required>
            </div>

            <!-- Phòng học -->
            <div class="form-group">
                <label for="roomID">Phòng học:</label>
                <select class="form-select" id="roomID" name="roomID" required>
                    <option value="1" ${schedule.roomID == 1 ? 'selected' : ''}>Phòng 1</option>
                    <option value="2" ${schedule.roomID == 2 ? 'selected' : ''}>Phòng 2</option>
                    <!-- Thêm các phòng học ở đây -->
                </select>
            </div>

            <!-- Lớp học -->
            <div class="form-group">
                <label for="classID">Lớp học:</label>
                <select class="form-select" id="classID" name="classID" required>
                    <option value="1" ${schedule.classID == 1 ? 'selected' : ''}>Lớp 1</option>
                    <option value="2" ${schedule.classID == 2 ? 'selected' : ''}>Lớp 2</option>
                    <!-- Thêm các lớp học ở đây -->
                </select>
            </div>

            <!-- Môn học -->
            <div class="form-group">
                <label for="subjectID">Môn học:</label>
                <select class="form-select" id="subjectID" name="subjectID" required>
                    <option value="1" ${schedule.subjectID == 1 ? 'selected' : ''}>Môn 1</option>
                    <option value="2" ${schedule.subjectID == 2 ? 'selected' : ''}>Môn 2</option>
                    <!-- Thêm các môn học ở đây -->
                </select>
            </div>

            <!-- Giảng viên -->
            <div class="form-group">
                <label for="lecturerID">Giảng viên:</label>
                <select class="form-select" id="lecturerID" name="lecturerID" required>
                    <option value="1" ${schedule.lecturerID == 1 ? 'selected' : ''}>Giảng viên 1</option>
                    <option value="2" ${schedule.lecturerID == 2 ? 'selected' : ''}>Giảng viên 2</option>
                    <!-- Thêm các giảng viên ở đây -->
                </select>
            </div>

            <!-- Nút Lưu và Hủy -->
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
                <a href="${pageContext.request.contextPath}/listSchedules" class="btn btn-secondary">Quay Lại</a>
            </div>
        </form>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
