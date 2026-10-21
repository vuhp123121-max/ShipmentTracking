## Tiến độ dự án

### 13/10/2026 — Khởi tạo project

- Tạo project Spring Boot với Maven và cấu hình Java.
- Tạo `Application` làm điểm khởi chạy ứng dụng.
- Tạo model `Shipment` cùng các thuộc tính và `ShipmentStatus`.
- Tạo khung ban đầu cho `ShipmentRepository`.
- Thêm endpoint kiểm tra ứng dụng `/ping`.
- Thêm các file cấu hình ban đầu cho Docker Compose, Dockerfile, GitHub Actions và build script.

### 17/10/2026 — Hoàn thiện Repository

- Bổ sung các thao tác dữ liệu cho `ShipmentRepository`, dùng `HashMap` để lưu shipment theo ID và tracking number.
- Thêm các chức năng lưu, tìm theo ID, tìm theo tracking number, lọc theo người gửi/trạng thái/ngày giao dự kiến, lấy toàn bộ và xóa shipment.
- Cập nhật model `Shipment` phục vụ các thao tác repository.

### 21/10/2026 — Phát triển Service, Menu và tài liệu

- Bổ sung `ShipmentService` để gọi repository và xử lý nghiệp vụ shipment.
- Thêm kiểm tra ID và tracking number trùng khi tạo shipment.
- Thêm quy tắc chuyển trạng thái `PENDING → IN_TRANSIT → DELIVERED`.
- Tạo menu terminal `ShipmentMenu` bằng `CommandLineRunner`.
- Bổ sung lựa chọn thêm, xem, tìm, lọc, cập nhật trạng thái và xóa shipment.
- Cập nhật README và tạo tài liệu roadmap các giai đoạn tiếp theo.
