<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.bean.Lecturer" %>
<%@ page import="model.bean.Account" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa Giảng Viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center text-primary">Chỉnh Sửa Giảng Viên</h1>

        <!-- Hiển thị thông báo lỗi (nếu có) -->
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null && !message.isEmpty()) { %>
            <div class="alert alert-danger"><%= message %></div>
        <% } %>

        <!-- Lấy dữ liệu Lecturer và danh sách Account -->
        <% 
            Lecturer lecturer = (Lecturer) request.getAttribute("lecturer");
        
        List<Account> accountList = (List<Account>) request.getAttribute("accountList");
    %>

        

        <!-- Form chỉnh sửa -->
        <form action="<%= request.getContextPath() %>/editLecturer" method="post">
            <!-- Mã giảng viên -->
            <input type="hidden" name="lecturerID" value="<%= lecturer != null ? lecturer.getLecturerID() : "" %>">

            <!-- Tên giảng viên -->
            <div class="mb-3">
                <label for="name" class="form-label">Tên Giảng Viên</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="<%= lecturer != null ? lecturer.getName() : "" %>">
            </div>

            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="<%= lecturer != null ? lecturer.getEmail() : "" %>">
            </div>

            <!-- Tài khoản -->
            <div class="mb-3">
                <label for="accountID" class="form-label">Tài Khoản</label>
                <select class="form-select" id="accountID" name="accountID">
                    <% if (accountList != null) { %>
                        <% for (Account account : accountList) { %>
                            <option value="<%= account.getAccountID() %>"
                                <%= (lecturer != null && lecturer.getAccount() != null 
                                     && account.getAccountID() == lecturer.getAccount().getAccountID()) ? "selected" : "" %>>
                                <%= account.getUsername() %>
                            </option>
                        <% } %>
                    <% } %>
                </select>
            </div>

            <!-- Nút submit -->
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
                <a href="<%= request.getContextPath() %>/listLecturers" class="btn btn-secondary">Quay Lại</a>
            </div>
        </form>
    </div>
</body>
</html>
