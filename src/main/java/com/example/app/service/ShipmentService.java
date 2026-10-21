package com.example.app.service;

import com.example.app.model.Shipment;
import com.example.app.model.Shipment.ShipmentStatus;
import com.example.app.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service 
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final Map<String, Shipment> findShipmentByString = new HashMap<>();
    private final Map<Long, Shipment> findShipmentByID = new HashMap<>();
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
    
///để Spring quản lý lớp này , shipmentRepository là đườnng để service làm việc dữ liệu
///Thêm shipment và từ chối mã trùng và từ chối các Số hiệu bị trùng
public Shipment addShipment(Shipment shipment) {
    if (shipmentRepository.exitsByID(shipment.getID())) {
        throw new IllegalArgumentException
        ("Shipment đã tồn tại " + shipment.getID());
    }
    else if (shipmentRepository.exitByTrackingNumber(shipment.getTrackingNumber())) {
        throw new IllegalArgumentException
        ("Số hiệu bi trùng " + shipment.getTrackingNumber());
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
    if (status == null)
    {
        throw new IllegalArgumentException("Trạng thái mới không được để trống " );
    }
    ShipmentStatus currentStatus = shipment.getStatus();
    //Cập nhật lại đúng trạng thái hiện tại thì thì không cần làm gì
    if(currentStatus == status)
    {
        return shipment;
    }
    boolean allowed = switch (currentStatus)
    {
        case PENDING -> status == ShipmentStatus.IN_TRANSIT;
        case IN_TRANSIT -> status == ShipmentStatus.DELIVERED;
        case DELIVERED -> false;
    };
    if(!allowed)
    {
        throw new IllegalArgumentException("Trạng thái không thành lý");
    }
    else
    {
        shipment.setStatus(status);
        shipmentRepository.save(shipment);
        return shipment;
    }
}
///Kiểm tra rồi xóa
public boolean deleteShipmentbyID(Long ID) {
    
    Shipment shipment = findShipmentByID.get(ID);
    if(shipment == null)
    {
        return false;
    }
    findShipmentByID.remove(ID);
    findShipmentByString.remove(shipment.getTrackingNumber());
    return true;
}
public boolean deleteShipmentByTrackingNumber(String trackingNumber) {
    Shipment shipment = findShipmentByString.get(trackingNumber);
    if(shipment == null)
    {
        return false;
    }
    findShipmentByString.remove(trackingNumber);
    findShipmentByID.remove(shipment.getID());
    return true;

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

