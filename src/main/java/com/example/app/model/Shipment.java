package com.example.app.model;


import java.time.LocalDate;
import java.time.LocalDateTime;



public class Shipment {
    Long ID;
    String trackingNumber;
    String senderName;
    String receiverName;
    String origin;
    String destination;
    ShipmentStatus status;
    LocalDateTime createdAt;
    LocalDate estimatedDeliveryDate; 


    public enum ShipmentStatus {
    PENDING, IN_TRANSIT, DELIVERED

}

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
