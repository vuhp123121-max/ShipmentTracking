# ShipmentTracking

Ứng dụng quản lý shipment đang được phát triển bằng Java 21 và Spring Boot, xây dựng với Maven.

## Tiến độ theo ngày

### 13/10/2026 — Khởi tạo dự án

- Tạo project Spring Boot với Maven và cấu hình Java.
- Tạo `Application` làm điểm khởi chạy ứng dụng.
- Tạo model `Shipment` cùng các thuộc tính và `ShipmentStatus`.
- Tạo khung ban đầu cho `ShipmentRepository`.
- Thêm endpoint kiểm tra ứng dụng `GET /ping`.
- Thêm các file cấu hình ban đầu cho Docker Compose, Dockerfile và GitHub Actions.

### 17/10/2026 — Repository in-memory

- Bổ sung thao tác lưu shipment bằng `HashMap`, tra cứu theo ID và tracking number.
- Thêm lọc theo người gửi, trạng thái và ngày giao dự kiến, cùng chức năng liệt kê shipment.
- Repository hiện vẫn dùng bộ nhớ; JPA/MySQL chưa được tích hợp vào luồng lưu trữ.

### 21/10/2026 — Service, menu và test ban đầu

- Thêm `ShipmentService` với kiểm tra ID/tracking number trùng và quy tắc chuyển trạng thái `PENDING → IN_TRANSIT → DELIVERED`.
- Tạo menu terminal `ShipmentMenu` với các lựa chọn thêm, xem, tìm, lọc, cập nhật trạng thái và xóa shipment.
- Tạo các lớp test cho model, repository và service; chưa xác nhận kết quả chạy test.
- Có cấu hình MySQL trong Docker Compose và `application.properties`.

### 24/10/2026 — Rà soát và cập nhật tiến độ

- Đối chiếu README với mã nguồn và cấu hình hiện có.
- Ghi nhận `/ping` là endpoint duy nhất; chưa có REST API nghiệp vụ CRUD.
- Xác nhận `docker/Dockerfile` và `.github/workflows/ci.yml` đang rỗng; Docker Compose hiện chỉ khai báo MySQL.
- Ghi nhận thao tác xóa trong service cần hoàn thiện để cập nhật repository đang được sử dụng.

### 01/11/2026 — PHASE5: Database & Persistence (đang thực hiện)

- Khởi động MySQL bằng Docker Compose và kết nối thành công từ DBeaver.
- Cập nhật `application.properties` để dùng `localhost:3306`, database `shipment_tracking` và tài khoản theo cấu hình Docker Compose.
- Ánh xạ `Shipment` thành JPA entity với bảng `shipments`; ID hiện vẫn do người dùng nhập, tracking number được đặt duy nhất và status lưu dạng chuỗi.
- Bắt đầu chuyển `ShipmentRepository` sang Spring Data JPA. Việc chuyển đổi chưa hoàn tất: tên `JpaRepository` đang bị gõ sai thành `JpaRepostiory`; service và repository tests vẫn còn phụ thuộc API in-memory cũ.
- Chưa xác nhận luồng lưu shipment từ ứng dụng vào MySQL.

## Trạng thái hiện tại

Dự án đang chuyển từ repository in-memory sang JPA/MySQL. MySQL đã kết nối được từ DBeaver và `Shipment` đã được ánh xạ thành entity; service/repository chưa đồng bộ hoàn toàn với Spring Data JPA.

## Hướng tiếp theo

1. Hoàn tất chuyển `ShipmentRepository` sang `JpaRepository` và đồng bộ `ShipmentService`.
2. Cập nhật repository tests và xác nhận dữ liệu shipment được lưu bền vững trong MySQL.
3. Hoàn thiện các phần còn lại của PHASE5.
4. Xây dựng REST API cho các nghiệp vụ shipment.
5. Hoàn thiện Dockerfile và Docker Compose cho ứng dụng cùng cơ sở dữ liệu.
6. Cấu hình GitHub Actions chạy test trước khi build.
7. Bổ sung monitoring, cloud deployment, Kubernetes và IaC.

## Công nghệ

- Java 21, Spring Boot, Maven
- MySQL 8 (đang tích hợp qua Spring Data JPA)
- Docker Compose
- GitHub Actions (workflow đã tạo nhưng chưa cấu hình)

## Chạy ứng dụng

```bash
mvnd spring-boot:run
```

Endpoint kiểm tra sau khi khởi động: `GET http://localhost:8080/ping`.
