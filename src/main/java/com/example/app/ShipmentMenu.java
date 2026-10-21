package com.example.app;


import com.example.app.service.ShipmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.app.model.Shipment;
import com.example.app.model.Shipment.ShipmentStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
@Component 
public class ShipmentMenu implements CommandLineRunner {

    private final ShipmentService shipmentService;

    public ShipmentMenu(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
@Override 
public void run(String... args) 
{
 Scanner sc = new Scanner(System.in);
 boolean running = true;
 while (running) 
    {
        printMenu();
        String choice = sc.nextLine();
        switch(choice)
        {
            case "1" ->
            {

                //Doc thong tin shipment roi goi addShipment
                System.out.println("Nhập ID: ");
                Long ID = Long.parseLong(sc.nextLine());

                System.out.println("Nhập số hiệu: ");
                String trackingNumber = sc.nextLine();

                System.out.println("Nhập người giao: ");
                String senderName = sc.nextLine();

                System.out.println("Nhập người nhan: ");
                String receiverName = sc.nextLine();

                System.out.println("Nhập Nơi gửi: ");
                String origin = sc.nextLine();

                System.out.println("Nhập Nơi nhan: ");
                String destination = sc.nextLine();

                System.out.println("Nhập ngày giao hành: ");
                LocalDate estimatedDeliveryDate = LocalDate.parse(sc.nextLine());
                
                LocalDateTime createdAt = LocalDateTime.now();

                Shipment shipment = new Shipment(ID, trackingNumber, senderName, receiverName, origin, destination, null, estimatedDeliveryDate);
                shipmentService.addShipment(shipment);

                    
            }
            case "2" ->
            {
                //Goi getAllShipment roi in danh sach
                shipmentService.getAllShipments()
                .forEach(System.out::println);
            }
            case "3" ->
            {
                //Tìm theo ID
                System.out.println("Nhập ID: ");
                Long ID = Long.parseLong(sc.nextLine());
                Shipment shipment = shipmentService.getShipmentByID(ID);
                System.out.println(shipment);
            }
            case "4" ->
            {
                //Tìm theo Số hiệu
                System.out.println("Nhập số hiệu: ");
                String trackingNumber = sc.nextLine();
                Shipment shipment = shipmentService.getShipmentByTrackingNumber(trackingNumber);
                System.out.println(shipment);
            }
            case "5" ->
            {
                //Lọc theo trạng thái
                System.out.println("Nhập trạng thái: ");
                ShipmentStatus status = ShipmentStatus.valueOf(sc.nextLine());
                shipmentService.getShipmentsByStatus(status)
                .forEach(System.out::println);
            }
            case "6"->
            {
                //Cập nhật trạng thái
                System.out.println("Nhập ID: ");
                Long ID = Long.parseLong(sc.nextLine());
                System.out.println("Nhập trạng thái: ");
                ShipmentStatus status = ShipmentStatus.valueOf(sc.nextLine());
                Shipment shipment = shipmentService.updateStatus(ID, status);
                System.out.println(shipment);

            }
            case "7"->
            {
                //Xóa số hiệu
                System.out.println("Nhập số hiệu cần xóa: ");
                String trackingNumber = sc.nextLine();
                boolean deleted = shipmentService.deleteShipmentByTrackingNumber(trackingNumber);
                System.out.println(deleted ? "Deleted successfully" : "Shipment not found");
            }
            case "8"->
            {
                //Tìm kiếm theo ngày giao hành
                System.out.println("Nhập ngày giao hành");
                LocalDate estimatedDeliveryDate = LocalDate.parse(sc.nextLine());
                shipmentService.getShipmentsByEstimatedDeliveryDate(estimatedDeliveryDate)
                .forEach(System.out::println);
            }
            case "9"->
            {
                //Tìm theo tên người giao
                System.out.println("Nhập tên người giao: ");
                String senderName = sc.nextLine();
                shipmentService.getShipmentsBySenderName(senderName)
                .forEach(System.out::println);
            }
            case "10"->
            {
                //Kiểm tra rồi xóaID
                System.out.println("Nhập ID cần xóa: ");
                Long ID = Long.parseLong(sc.nextLine());
                boolean deleted = shipmentService.deleteShipmentbyID(ID);
                System.out.println(deleted ? "Deleted successfully" : "Shipment not found");
                
                
            }
            case "0" -> running = false;
                default -> System.out.println("Invalid option");
            
        }
 
  }
}
private void printMenu()
{
    System.out.println("1. Add shipment" + "\n") ;
    System.out.println("2. Get all shipments" + "\n");
    System.out.println("3. Find By ID" + "\n");
    System.out.println("4. Find By Tracking Number"+ "\n");
    System.out.println("5. Find By Status"+ "\n");
    System.out.println("6. Update Status"+ "\n");
    System.out.println("7. Find By Tracking Number"+ "\n");
    System.out.println("8. Find By Estimated Delivery Date"+ "\n");
    System.out.println("9. Find By Sender Name"+ "\n");
    System.out.println("10. Delete By ID"+ "\n");
    System.out.println("0. Exit"+ "\n");
    System.out.println("Chọn chức năng:"+ "\n");
}
}