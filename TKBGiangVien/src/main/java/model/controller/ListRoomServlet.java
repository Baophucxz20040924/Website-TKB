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
            // Lấy danh sách phòng học từ cơ sở dữ liệu
            ArrayList<Room> roomList = (ArrayList<Room>) roomBO.getAllRooms();

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
