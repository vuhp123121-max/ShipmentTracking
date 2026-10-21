## Tiến độ ngày 2026-10-21

- Xây dựng `ShipmentMenu` bằng `CommandLineRunner` để hiển thị menu terminal khi ứng dụng Spring Boot khởi động.
- Bổ sung các lựa chọn: thêm shipment, xem danh sách, tìm theo ID hoặc tracking number, lọc theo trạng thái/ngày giao dự kiến/người gửi, cập nhật trạng thái và xóa.
- Khi tạo shipment, trạng thái mặc định là `PENDING`.
- Bổ sung kiểm tra ID và tracking number trùng trong `ShipmentService`.
- Bổ sung quy tắc chuyển trạng thái: `PENDING → IN_TRANSIT → DELIVERED`; từ chối chuyển trạng thái không hợp lệ.

Các mục cần sửa trước khi ghi là hoàn tất:

- ShipmentMenu tạo createdAt nhưng truyền null vào constructor. Hãy truyền biến createdAt thay cho null.
- Service đang xóa từ các Map riêng, trong khi thêm shipment lưu vào repository. Hơn nữa, các phương thức xóa trong repository hiện đang bị comment. Vì vậy, xóa theo ID/tracking number hiện chưa hoạt động đúng.
- Dòng hiển thị menu cho lựa chọn 7 vẫn ghi “Find By Tracking Number”, dù case 7 hiện xóa theo tracking number.
