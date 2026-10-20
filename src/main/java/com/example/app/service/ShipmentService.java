package com.example.app.service;

import com.example.app.model.Shipment;
import com.example.app.model.Shipment.ShipmentStatus;
import com.example.app.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service 
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
    
///để Spring quản lý lớp này , shipmentRepository là đườnng để service làm việc dữ liệu
///Thêm shipment và từ chối mã trùng
public Shipment addShipment(Shipment shipment) {
    if (shipmentRepository.exitsByID(shipment.getID())) {
        throw new IllegalArgumentException
        ("Shipment đã tồn tại " + shipment.getID());
    }
    shipmentRepository.save(shipment);
    return shipment;
}
///Tìm theo ID và lấy toàn bộ
public Shipment getShipmentByID(Long ID) {
    return shipmentRepository.findByID(ID).orElseThrow(()-> new IllegalArgumentException("Không tìm thấy " + ID));
}
public List<Shipment> getAllShipments() {
    return shipmentRepository.findall();
}
///Lọc theo trạng thái
public List<Shipment> getShipmentsByStatus(ShipmentStatus status) {
    return shipmentRepository.findByStatus(status);
}
///Cập nhật trạng thái
public Shipment updateStatus(Long ID, ShipmentStatus status) {
    Shipment shipment = getShipmentByID(ID);
    shipment.setStatus(status);
    shipmentRepository.save(shipment);
    return shipment;
}
///Kiểm tra rồi xóa
public boolean deleteShipment(Long ID) {
    return shipmentRepository.deletebyID(ID);
}
///tìm kiếm theo Số hiệu
public Shipment getShipmentByTrackingNumber(String trackingNumber) {
    return shipmentRepository.findByTrackingNumber(trackingNumber)
    .orElseThrow(()-> new IllegalArgumentException("Không tìm thấy " + trackingNumber));
}
///Tìm Theo Tên người giao
public List<Shipment> getShipmentsBySenderName(String senderName) {
    return shipmentRepository.findBySenderName(senderName);

    
}
///Tìm theo Ngày giao hành
public List<Shipment> getShipmentsByEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
    return shipmentRepository.findByEstimatedDeliveryDate(estimatedDeliveryDate);
}
}

