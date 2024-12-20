<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Schedule" %>
<%@ page import="model.bean.Lecturer" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thời Khóa Biểu Giảng Viên</title>

    <!-- Link đến Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa; /* Màu nền */
        }

        .container {
            margin-top: 50px;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #007bff;
            font-weight: bold;
        }

        .btn {
            padding: 8px 15px;
            font-size: 14px;
        }

        table {
            margin-top: 20px;
        }

        .table th {
            text-align: center;
            background-color: #007bff;
            color: #fff;
        }

        .text-center a {
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Danh Sách Thời Khóa Biểu Của Giảng Viên</h1>

        <!-- Hiển thị bảng danh sách thời khóa biểu của giảng viên -->
        <table class="table table-hover table-bordered text-center">
            <thead>
                <tr>
                    <th>Ngày</th>
                    <th>Môn Học</th>
                    <th>Lớp Học</th>
                    <th>Phòng Học</th>
                    <th>Thời Gian</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Schedule> scheduleList = (List<Schedule>) request.getAttribute("scheduleList");
                    if (scheduleList != null && !scheduleList.isEmpty()) {
                        for (Schedule schedule : scheduleList) {
                %>
                        <tr>
                            <td><%= schedule.getDayOfWeek() %></td>
                            <td><%= schedule.getSubjectName() %></td>
                            <td><%= schedule.getClassName() %></td>
                            <td><%= schedule.getRoomName() %></td>
                            <td><%= schedule.getStartTime() + " - " + schedule.getEndTime() %></td>
                        </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5" class="text-center">
                            <div class="alert alert-warning mb-0" role="alert">
                                Không có thời khóa biểu cho giảng viên này.
                            </div>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href="<%= request.getContextPath() %>/homeLecturer" class="btn btn-primary">Quay lại</a>
        </div>
    </div>

    <!-- Link đến Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
