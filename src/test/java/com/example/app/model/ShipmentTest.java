package com.example.app.model;

import com.example.app.model.Shipment.ShipmentStatus;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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
