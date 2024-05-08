from enum import Enum
from datetime import datetime, timedelta

class VehicleType(Enum):
    SMALL = 1
    MEDIUM = 2
    LARGE = 3

class Vehicle:
    def __init__(self, license_number, type):
        self.license_number = license_number
        self.type = type

class Car(Vehicle):
    def __init__(self, license_number, type):
        super().__init__(license_number, type)
        # Add additional attributes specific to Car if needed

class Bike(Vehicle):
    def __init__(self, license_number, type):
        super().__init__(license_number, type)
        # Add additional attributes specific to Bike if needed

class ParkingTicket:
    def __init__(self, parking_id, type):
        self.parking_id = parking_id
        self.type = type
        self.issued_at = datetime.now()
        self.is_paid = False
        self.total_fee = 0.0
        self.paid_at = None

    def mark_paid(self, total_fee):
        self.total_fee = total_fee
        self.paid_at = datetime.now()
        self.is_paid = True

class TicketCostCalculator:
    @staticmethod
    def get_vehicle_hourly_rate_by_vehicle_type(type):
        if type == VehicleType.SMALL:
            return 20.00
        elif type == VehicleType.MEDIUM:
            return 50.00
        elif type == VehicleType.LARGE:
            return 100.00
        else:
            raise ValueError("Unknown vehicle type")

    @staticmethod
    def total_cost(entry_time, exit_time, type):
        hourly_rate = TicketCostCalculator.get_vehicle_hourly_rate_by_vehicle_type(type)
        total_parking_time = (exit_time - entry_time).total_seconds() / 3600
        return total_parking_time * hourly_rate

class ParkingLot:
    def __init__(self):
        self.parking_floors = []
        self.issued_tickets = []

    def find_available_spot(self, type):
        # implement logic to find available spot by vehicle type
        return None

    def issue_parking_ticket(self, vehicle):
        spot = self.find_available_spot(vehicle.type)
        if spot:
            spot.occupy_spot()
            ticket = ParkingTicket(self.generate_ticket_id(), vehicle.type)
            self.issued_tickets.append(ticket)
            return ticket
        return None

    def process_payment(self, entry_time, exit_time, type):
        return TicketCostCalculator.total_cost(entry_time, exit_time, type)

    def generate_ticket_id(self):
        return "TICKET_" + str(int(datetime.now().timestamp() * 1000))

class ParkingFloor:
    def __init__(self, floor_no, total_parking_capacity):
        self.floor_no = floor_no
        self.total_parking_capacity = total_parking_capacity

    def set_total_capacity_by_vehicle_type(self, type):
        # implement logic
        pass

    def get_total_available_parking_spot_by_vehicle_type(self, type):
        # implement logic
        return 0

class ParkingSpot:
    def __init__(self, id, type):
        self.id = id
        self.type = type
        self.is_occupied = False

    def occupy_spot(self):
        self.is_occupied = True

    def vacant_spot(self):
        self.is_occupied = False
