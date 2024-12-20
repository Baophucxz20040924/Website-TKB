package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bo.RoomBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteRoomServlet", urlPatterns = {"/deleteRoom"})
public class DeleteRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RoomBO roomBO;

    public DeleteRoomServlet() {
        this.roomBO = new RoomBO(); // Khởi tạo đối tượng RoomBO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy roomID từ tham số URL
        String roomIDParam = request.getParameter("roomID");
        int roomID;

        try {
            roomID = Integer.parseInt(roomIDParam); // Chuyển đổi roomID sang kiểu số nguyên
        } catch (NumberFormatException e) {
            // Nếu roomID không hợp lệ, hiển thị lỗi và quay lại danh sách phòng học
            request.setAttribute("message", "ID phòng học không hợp lệ!");
            request.getRequestDispatcher("listRooms").forward(request, response);
            return;
        }

        try {
            // Gọi phương thức deleteRoom từ RoomBO
            boolean isDeleted = roomBO.deleteRoom(roomID);

            if (isDeleted) {
                // Nếu xóa thành công, chuyển hướng về danh sách phòng học
                request.setAttribute("message", "Xóa phòng học thành công!");
            } else {
                // Nếu xóa thất bại, hiển thị lỗi
                request.setAttribute("message", "Xóa phòng học thất bại! ID không tồn tại.");
            }

            // Chuyển hướng về danh sách phòng học
            request.getRequestDispatcher("listRooms").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Hiển thị lỗi nếu có lỗi từ database
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("listRooms").forward(request, response);
        }
    }
}
