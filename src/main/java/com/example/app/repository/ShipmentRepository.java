package com.example.app.repository;
import org.springframework.stereotype.Repository;
 
import com.example.app.model.Shipment;
import com.example.app.model.Shipment.ShipmentStatus;

import java.util.*;
import java.time.*;
@Repository 
public class ShipmentRepository {
    ///Thao tác 1
    private final Map<String, Shipment> findShipmentByString = new HashMap<>();
    private final Map<Long, Shipment> findShipmentByID = new HashMap<>();
    
    
    public void save(Shipment shipment)
    {
        findShipmentByString.put(shipment.getTrackingNumber(), shipment);
        findShipmentByID.put(shipment.getID(), shipment);
    }
    public boolean deletebyID(Long ID)
    {
        Shipment removedShipment = findShipmentByID.remove(ID);
        return removedShipment != null;

    }
    ///Thao tác riêng: Kiểm tra theo mã ID đã có tồn tại hay chưa
    public boolean exitsByID(Long ID) {
        return findShipmentByID.containsKey(ID);
    }
    ///Thao tác 2
    public Optional<Shipment> findByID(Long ID) {
        return Optional.ofNullable(findShipmentByID.get(ID));
    }
    public Optional<Shipment> findByTrackingNumber(String trackingNumber) {
        return Optional.ofNullable(findShipmentByString.get(trackingNumber));
    }
    ///Thao Tac 2.1 Đơn Lẻ(Done)(Chỉ được lấy 1 đối tượng đơn lẻ)
    ///Thao tác 2.2 Danh sách và lọc dữ liệu
    public List<Shipment> findBySenderName(String senderName)
    {
        return findShipmentByID.values().stream()
               .filter(s -> s.senderName().equals(senderName))
               .toList();
    }
    public List<Shipment> findByStatus(ShipmentStatus status)
    {
        return findShipmentByID.values().stream()
               .filter(s -> s.getStatus().equals(status))
               .toList();
    }
    public List<Shipment> findByEstimatedDeliveryDate(LocalDate estimatedDeliveryDate)
    {
        return findShipmentByID.values().stream()
               .filter(s -> s.getEstimatedDeliveryDate().equals(estimatedDeliveryDate))
               .toList();
    }
    ///Lay toan bo
    public List<Shipment> findall()
    {
        return new ArrayList<>(findShipmentByID.values());
    }





}
