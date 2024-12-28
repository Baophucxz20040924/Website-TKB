package model.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Room;
import model.bo.RoomBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListRoomServlet", urlPatterns = {"/listRooms"})
public class ListRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Tạo đối tượng RoomBO để sử dụng các phương thức quản lý phòng học
    private RoomBO roomBO;

    public ListRoomServlet() {
        this.roomBO = new RoomBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String capacityParam = request.getParameter("capacity");
            List<Room> roomList;

            // Kiểm tra nếu có tham số capacity được gửi từ form
            if (capacityParam != null && !capacityParam.isEmpty()) {
                try {
                    int capacity = Integer.parseInt(capacityParam);
                    // Lọc danh sách phòng học theo sức chứa
                    roomList = roomBO.getRoomsByCapacity(capacity);
                    request.setAttribute("filterMessage", "Danh sách phòng có sức chứa lớn hơn " + capacity);
                } catch (NumberFormatException e) {
                    // Xử lý khi capacity không hợp lệ
                    request.setAttribute("message", "Giá trị sức chứa không hợp lệ!");
                    roomList = new ArrayList<>();
                }
            } else {
                // Nếu không có tham số, lấy tất cả phòng
                roomList = roomBO.getAllRooms();
            }

            // Đặt danh sách phòng học vào request attribute
            request.setAttribute("roomList", roomList);

            // In ra console để kiểm tra dữ liệu
            System.out.println("Danh sách phòng học:");
            for (Room room : roomList) {
                System.out.println(room.getRoomName());
            }

            // Chuyển tiếp request tới JSP để hiển thị danh sách phòng học
            request.getRequestDispatcher("listRooms.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi nếu có lỗi xảy ra
            request.setAttribute("message", "Lỗi khi lấy danh sách phòng học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
