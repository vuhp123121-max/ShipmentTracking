package com.example.app.service;

import com.example.app.model.Shipment;
import com.example.app.model.Shipment.ShipmentStatus;
import com.example.app.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentService shipmentService;

    private Shipment shipment;

    @BeforeEach
    void setUp() {
        shipment = new Shipment(1L, "TN001", "Sender A", "Receiver A", "Hanoi", "Da Nang", null, LocalDate.of(2026, 11, 1));
    }

    @Test
    void addShipment_shouldSaveWhenIdAndTrackingNumberAreUnique() {
        when(shipmentRepository.exitsByID(1L)).thenReturn(false);
        when(shipmentRepository.exitByTrackingNumber("TN001")).thenReturn(false);

        Shipment result = shipmentService.addShipment(shipment);

        assertNotNull(result);
        verify(shipmentRepository, times(1)).save(shipment);
    }

    @Test
    void addShipment_shouldThrowWhenIdExists() {
        when(shipmentRepository.exitsByID(1L)).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> shipmentService.addShipment(shipment));
        assertEquals("Shipment đã tồn tại 1", ex.getMessage());
    }

    @Test
    void addShipment_shouldThrowWhenTrackingNumberDuplicated() {
        when(shipmentRepository.exitsByID(1L)).thenReturn(false);
        when(shipmentRepository.exitByTrackingNumber("TN001")).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> shipmentService.addShipment(shipment));
        assertEquals("Số hiệu bị trùng TN001", ex.getMessage());
    }

    @Test
    void getShipmentByID_shouldReturnShipmentWhenExists() {
        when(shipmentRepository.findByID(1L)).thenReturn(Optional.of(shipment));

        Shipment result = shipmentService.getShipmentByID(1L);

        assertEquals(shipment, result);
        verify(shipmentRepository, times(1)).findByID(1L);
    }

    @Test
    void getShipmentByID_shouldThrowWhenNotFound() {
        when(shipmentRepository.findByID(1L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> shipmentService.getShipmentByID(1L));
        assertEquals("Không tìm thấy 1", ex.getMessage());
    }

    @Test
    void updateStatus_shouldChangePendingToInTransit() {
        shipment.setStatus(ShipmentStatus.PENDING);
        when(shipmentRepository.findByID(1L)).thenReturn(Optional.of(shipment));

        Shipment result = shipmentService.updateStatus(1L, ShipmentStatus.IN_TRANSIT);

        assertEquals(ShipmentStatus.IN_TRANSIT, result.getStatus());
        verify(shipmentRepository, times(1)).save(shipment);
    }

    @Test
    void updateStatus_shouldThrowWhenFromDeliveredToInTransit() {
        shipment.setStatus(ShipmentStatus.DELIVERED);
        when(shipmentRepository.findByID(1L)).thenReturn(Optional.of(shipment));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> shipmentService.updateStatus(1L, ShipmentStatus.IN_TRANSIT));
        assertEquals("Trạng thái không thành lý", ex.getMessage());
    }

    @Test
    void updateStatus_shouldThrowWhenPendingToDeliveredDirectly() {
        shipment.setStatus(ShipmentStatus.PENDING);
        when(shipmentRepository.findByID(1L)).thenReturn(Optional.of(shipment));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> shipmentService.updateStatus(1L, ShipmentStatus.DELIVERED));
        assertEquals("Trạng thái không thành lý", ex.getMessage());
    }

    @Test
    void deleteShipmentbyID_shouldReturnTrueWhenExists() {
        when(shipmentRepository.findByID(1L)).thenReturn(Optional.of(shipment));

        boolean result = shipmentService.deleteShipmentbyID(1L);

        assertTrue(result);
    }

    @Test
    void deleteShipmentbyID_shouldReturnFalseWhenNotFound() {
        when(shipmentRepository.findByID(1L)).thenReturn(Optional.empty());

        boolean result = shipmentService.deleteShipmentbyID(1L);

        assertFalse(result);
    }
}
