<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lỗi Hệ Thống</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .error-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .error-container h1 {
            color: #e74c3c;
        }
        .error-container p {
            color: #333;
        }
        .back-link {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 20px;
            color: #fff;
            background-color: #3498db;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-link:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Đã xảy ra lỗi!</h1>
        <p>${message}</p>
    </div>
</body>
</html>
