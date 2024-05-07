import java.util.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
      System.out.println("Design a parking system");
  }
}

// vehicle to park in parking lot.
// Entites : Vehicle, ParkingTicket, ParkingLot, ParkingFloor


// Vehicle entity
abstract class Vehicle{
  
  private String licenseNumber;
  protected VehicleType type;
  
  public Vehicle(String licenseNumber, VehicleType type){
    this.licenseNumber = licenseNumber;
    this.type = type;
  }
}

public enum VehicleType{
  LARGE, MEDIUM, SMALL
}

public class Car extends Vehicle{
  
   public Car(String licenseNumber, VehicleType type) {
        super(licenseNumber, type);
        // Add additional attributes specific to Car if needed
    }
}

public class Bike extends Vehicle{
  
      public Bike(String licenseNumber, VehicleType type) {
        super(licenseNumber, type);
        // Add additional attributes specific to Bike if needed
    }
}

// Ticket entity
public class ParkingTicket {
  
  private String parkingId;
  private VehicleType type;
  private LocalDateTime issuedAt;
  private boolean isPaid;
  private double totalFee;
  private LocalDateTime paidAt;
  
  public ParkingTicket(String parkingId, VehicleType type){
    this.parkingId = parkingId;
    this.type = type;
    this.issuedAt = LocalDateTime.now();
    this.isPaid = false;
  }
  
  public void markPaid(double totalFee){
    this.totalFee = totalFee;
    this.paidAt = LocalDateTime.now();
    this.isPaid = true;
  }
}

abstract class TicketCostCalculator{
  
    protected static double getVehicleHourlyRateByVehicleType(VehicleType type){
    switch(type){
      case SMALL:
        return 20.00;
      case MEDIUM:
        return 50.00;
      case LARGE:
        return 100.00;
      default:
        throw new IllegalArgumentException("Unknown vehicle type");
    }
  }
    
  protected static double totalCost(LocalDateTime entryTime, LocalDateTime exitTime, 
  VehicleType type ){
    double hourlyRate = getVehicleHourlyRateByVehicleType(type);
    long totalParkingTime = getTotalHours(entryTime, exitTime);
    return totalParkingTime * hourlyRate;
  }  
  private static long getTotalHours(LocalDateTime entryTime, LocalDateTime exitTime){
    return ChronoUnit.HOURS.between(entryTime, exitTime);
  } 
}

// parking lot entity
public class ParkingLot{
  
  private List<ParkingFloor> parkingFloors;
  private List<ParkingTicket> issuedTickets;
  
  public ParkingLot(){
    this.parkingFloors = new ArrayList<>();
    this.issuedTickets = new ArrayList();
  }
  
  public ParkingSpot findAvailableSpot(VehicleType type){
    // implement logic to find available spot by vehicle type
    return null;
  }
  
  public ParkingTicket issueParkingTicket(Vehicle vehicle){
    ParkingSpot spot = findAvailableSpot(vehicle.type);
    if(spot!=null){
      spot.occupySpot();
      ParkingTicket ticket = new ParkingTicket(generateTicketId(), vehicle.type);
      issuedTickets.add(ticket);
      return ticket;
    }
    return null;
  }
  
  public double processPayment(LocalDateTime entryTime, LocalDateTime exitTime, VehicleType type){
    return TicketCostCalculator.totalCost(entryTime, exitTime, type);
  }
  
   private String generateTicketId(){
    return "TICKET_"+ System.currentTimeMillis();
  } 
}

public class ParkingFloor{
  
  private int floorNo;
  private long totalParkingCapacity;
  
  public ParkingFloor(int floorNo, long totalParkingCapacity){
    this.floorNo = floorNo;
    this.totalParkingCapacity = totalParkingCapacity;
  }
  
  public void setTotalCapacityByVehicleType(VehicleType type){
    // implement logic
  }
  public long getTotalAvailableParkingSpotByVehicleType(VehicleType type){
    
    // implement logic
    
    return 0L;
  } 
}

public class ParkingSpot{
  
  private String id;
  private VehicleType type;
  private boolean isOccupied;
  
  public ParkingSpot(String id, VehicleType type){
    this.id = id;
    this.type = type;
    this.isOccupied = false;
  }
  
  public void occupySpot(){
    this.isOccupied = true;
  }
  
  public void vacantSpot(){
    this.isOccupied = false;
  }
}
