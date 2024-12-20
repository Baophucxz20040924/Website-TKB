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

@WebServlet(name = "EditRoomServlet", urlPatterns = {"/editRoom"})
public class EditRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RoomBO roomBO;

    public EditRoomServlet() {
        this.roomBO = new RoomBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomIDParam = request.getParameter("roomID");

        try {
            // Kiểm tra nếu roomIDParam là null hoặc rỗng
            if (roomIDParam == null || roomIDParam.isEmpty()) {
                throw new IllegalArgumentException("RoomID is required.");
            }

            // Parse roomID từ chuỗi sang số nguyên
            int roomID = Integer.parseInt(roomIDParam);

            // Lấy thông tin phòng học từ RoomBO
            Room room = roomBO.getRoomById(roomID);

            if (room == null) {
                throw new NullPointerException("Room not found for ID: " + roomID);
            }

            // Gán thông tin phòng học vào request attribute
            request.setAttribute("room", room);

            // Chuyển tiếp tới trang chỉnh sửa phòng học
            request.getRequestDispatcher("editRoomForm.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi
            request.setAttribute("message", "Lỗi khi tải thông tin phòng học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form chỉnh sửa
            String roomIDParam = request.getParameter("roomID");
            String roomName = request.getParameter("roomName");
            String capacityParam = request.getParameter("capacity");

            // Kiểm tra dữ liệu đầu vào
            if (roomIDParam == null || roomIDParam.isEmpty()) {
                throw new IllegalArgumentException("RoomID is required.");
            }
            if (roomName == null || roomName.trim().isEmpty()) {
                throw new IllegalArgumentException("RoomName is required.");
            }
            if (capacityParam == null || capacityParam.isEmpty()) {
                throw new IllegalArgumentException("Capacity is required.");
            }

            // Parse dữ liệu từ chuỗi sang kiểu phù hợp
            int roomID = Integer.parseInt(roomIDParam);
            int capacity = Integer.parseInt(capacityParam);

            // Tạo đối tượng Room và gán giá trị
            Room room = new Room();
            room.setRoomID(roomID);
            room.setRoomName(roomName);
            room.setCapacity(capacity);

            // Gọi BO để cập nhật phòng học
            boolean isUpdated = roomBO.updateRoom(room);

            if (isUpdated) {
                // Nếu cập nhật thành công, chuyển hướng về danh sách phòng học
                response.sendRedirect("listRooms");
            } else {
                // Nếu cập nhật thất bại, hiển thị thông báo lỗi
                request.setAttribute("message", "Cập nhật phòng học thất bại!");
                request.setAttribute("room", room); // Giữ lại dữ liệu để hiển thị lại form
                request.getRequestDispatcher("editRoomForm.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng tới trang lỗi nếu có ngoại lệ
            request.setAttribute("message", "Đã xảy ra lỗi khi cập nhật phòng học: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
