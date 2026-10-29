package com.example.app.model;

import com.example.app.model.Shipment.ShipmentStatus;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/// Về file test model: Sẽ xác nhận đã gán đúng tất cả các thuộc tính chưa
/// xác nhận các Status thay đổi theo trạng thái
/// Sẽ có 2 phương thức hàm chính:
///     constructor_shouldSetAllFields: Kiểm tra hàm khởi tạo đảm bảo các đối tượng thuộc tính đều đã được gán đúng đối tượng
///     set Status_shouldUpdateStatus(): Kiểm tra phương thức setStatus() cập nhật trạng thái mới
class ShipmentTest {

    @Test
    void constructor_shouldSetAllFields() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.of(2026, 11, 1);

        Shipment shipment = new Shipment(1L, "TN001", "Sender A", "Receiver A", "Hanoi", "Da Nang", now, date);

        assertEquals(1L, shipment.getID());
        assertEquals("TN001", shipment.getTrackingNumber());
        assertEquals("Sender A", shipment.senderName());
        assertEquals("Receiver A", shipment.getReceiverName());
        assertEquals("Hanoi", shipment.getOrigin());
        assertEquals("Da Nang", shipment.getDestination());
        assertEquals(ShipmentStatus.PENDING, shipment.getStatus());
        assertEquals(now, shipment.getCreatedAt());
        assertEquals(date, shipment.getEstimatedDeliveryDate());
    }

    @Test
    void setStatus_shouldUpdateStatus() {
        Shipment shipment = new Shipment(1L, "TN001", "Sender A", "Receiver A", "Hanoi", "Da Nang", null, LocalDate.of(2026, 11, 1));

        shipment.setStatus(ShipmentStatus.IN_TRANSIT);

        assertEquals(ShipmentStatus.IN_TRANSIT, shipment.getStatus());
    }
}
