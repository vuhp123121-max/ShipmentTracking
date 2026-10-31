package com.example.app.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


///PHASE5: Chuyển Shipment thành JPA Entity
/// Các annotation JPA giúp Sping/Hibernate hiểu cách lưu object đó vào MySQL

@Entity 
@Table(name = "shipments")
public class Shipment {
    
    @Id 
    @Column (name = "shipment_id", nullable = false, unique = true)
    Long ID;
    @Column (name = "tracking_number", nullable = false, unique = true)
    String trackingNumber;
    String senderName;
    String receiverName;
    String origin;
    String destination;
    @Enumerated (EnumType.STRING)
    @Column (name = "status", nullable = false)
    ShipmentStatus status;
    LocalDateTime createdAt;
    LocalDate estimatedDeliveryDate; 


    public enum ShipmentStatus {
    PENDING, IN_TRANSIT, DELIVERED

}
protected Shipment() {}

 public Shipment(Long ID, String trackingNumber, String senderName, String receiverName, String origin, String destination, LocalDateTime createdAt, LocalDate estimatedDeliveryDate ) 
    {
        this.ID = ID;
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.origin = origin;
        this.destination = destination;
        this.status = ShipmentStatus.PENDING;
        this.createdAt = createdAt;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }
    public Long getID(){
        return ID; 
    }
    public String getTrackingNumber(){
        return trackingNumber;
    }
    public String senderName(){
        return senderName;
    }
    public String getReceiverName(){
        return receiverName;
    }
    public String getOrigin(){
        return origin;
    }
    public String getDestination(){
        return destination;
    }
    
    public ShipmentStatus getStatus(){
        return status;
    }
    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDate getEstimatedDeliveryDate(){
        return estimatedDeliveryDate;
    }
    
    
@Override 
    public String toString()
    {
        return
        "Shipment: " +
        "ShipmentID" + ID + "\n" +
        "Tracking Number: " + trackingNumber + "\n" +
        "Sender Name: " + senderName + "\n" +
        "Receiver Name: " + receiverName + "\n" +
        "Origin: " + origin + "\n" +
        "Destination: " + destination + "\n" +
        "Status: " + status + "\n" +
        "Created At: " + createdAt + "\n" +
        "Estimated Delivery Date: " + estimatedDeliveryDate + "\n";
    }

}
