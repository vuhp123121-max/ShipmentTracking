package com.example.app.repository;

import com.example.app.model.Shipment;
import com.example.app.model.Shipment.ShipmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentRepositoryTest {

    private ShipmentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ShipmentRepository();
    }

    @Test
    void save_and_findByID_shouldReturnSameShipment() {
        Shipment shipment = new Shipment(1L, "TN001", "Sender A", "Receiver A", "Hanoi", "Da Nang", null, LocalDate.of(2026, 11, 1));
        repository.save(shipment);

        Shipment found = repository.findByID(1L).orElse(null);
        assertNotNull(found);
        assertEquals("TN001", found.getTrackingNumber());
    }

    @Test
    void exitsByID_shouldReturnTrueWhenExists() {
        repository.save(new Shipment(1L, "TN001", "Sender", "Receiver", "Hanoi", "Da Nang", null, LocalDate.of(2026, 11, 1)));
        assertTrue(repository.exitsByID(1L));
    }

    @Test
    void exitsByID_shouldReturnFalseWhenNotFound() {
        assertFalse(repository.exitsByID(999L));
    }

    @Test
    void findByStatus_shouldReturnOnlyMatchingShipments() {
        repository.save(new Shipment(1L, "TN001", "Sender", "Receiver", "Hanoi", "Da Nang", null, LocalDate.of(2026, 11, 1)));

        Shipment shipment2 = new Shipment(2L, "TN002", "Sender", "Receiver", "Hanoi", "Da Nang", null, LocalDate.of(2026, 11, 1));
        shipment2.setStatus(ShipmentStatus.IN_TRANSIT);
        repository.save(shipment2);

        List<Shipment> byStatus = repository.findByStatus(ShipmentStatus.PENDING);
        assertEquals(1, byStatus.size());
    }

    @Test
    void findall_shouldReturnAllSavedShipments() {
        repository.save(new Shipment(1L, "TN001", "Sender", "Receiver", "Hanoi", "Da Nang", null, LocalDate.of(2026, 11, 1)));
        repository.save(new Shipment(2L, "TN002", "Sender", "Receiver", "Hanoi", "Da Nang", null, LocalDate.of(2026, 11, 1)));

        List<Shipment> all = repository.findall();
        assertEquals(2, all.size());
    }
}
