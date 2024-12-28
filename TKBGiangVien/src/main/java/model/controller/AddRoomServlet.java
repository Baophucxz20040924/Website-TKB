package model.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Room;
import model.bo.RoomBO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddRoomServlet", urlPatterns = {"/addRoom"})
public class AddRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RoomBO roomBO;

    public AddRoomServlet() {
        this.roomBO = new RoomBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến form thêm phòng học
        request.getRequestDispatcher("addRoomForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomName = request.getParameter("roomName");
        int capacity = 0;

        try {
            // Kiểm tra và chuyển đổi dữ liệu
            capacity = Integer.parseInt(request.getParameter("capacity"));

            // Tạo đối tượng Room
            Room room = new Room();
            room.setRoomName(roomName);
            room.setCapacity(capacity);

            // Thêm phòng học vào cơ sở dữ liệu
            boolean isAdded = roomBO.addRoom(room);

            if (isAdded) {
                // Hiển thị thông báo thành công
                request.setAttribute("message", "Thêm phòng học thành công!");
            } else {
                // Hiển thị thông báo thất bại
                request.setAttribute("message", "Thêm phòng học thất bại!");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Dung lượng phòng phải là một số nguyên hợp lệ!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
        }

        // Quay lại form thêm phòng học
        request.getRequestDispatcher("addRoomForm.jsp").forward(request, response);
    }
}
