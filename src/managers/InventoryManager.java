package managers;

import models.*;

import java.util.ArrayList;

import exceptions.*;

public class InventoryManager {

    // 1. Assign Equipment with availability and limit checks
    public void assignEquipment(StaffMember staff, Equipment equipment) 
            throws EquipmentNotAvailableException, AssignmentLimitExceededException {
        
        if (!equipment.isAvailable()) {
            throw new EquipmentNotAvailableException("Item " + equipment.getName() + " is already assigned.");
        } else if (staff.getAssignedEquipmentCount() >= 5) {
            throw new AssignmentLimitExceededException(staff.getName() + " has reached the assignment limit.");
        } else {
            staff.addAssignedEquipment(equipment);
            equipment.setAvailable(false);
            System.out.println("Success: Assigned " + equipment.getName() + " to " + staff.getName());
        }
    }

    // 2. Return Equipment and update availability
    public void returnEquipment(StaffMember staff, String assetId) {
        boolean check = staff.removeAssignedEquipment(assetId);
        if (check) {
	        // In a real system, you would find the specific equipment object to setAvailable(true)
	        System.out.println("Success: Equipment " + assetId + " returned by " + staff.getName());
        }
    }

    // 3. Calculate Fee using a Switch Statement
    public double calculateMaintenanceFee(Equipment equipment, int daysOverdue) {
        double baseRate;
        // switch works on String categories
        switch (equipment.getItemType().toLowerCase()) {
            case "labequipment":
                baseRate = 50.0;
                break;
            case "equipment":
                baseRate = 20.0;
                break;
            default:
                baseRate = 10.0;
                break;
        }
        return baseRate * daysOverdue;
    }

    // 4. Overloaded Search Methods
    public void searchEquipment(String name, ArrayList<InventoryItem> inventory) {
    	boolean found = false;
        System.out.println("\n--- Search Results for: " + name + " ---");

        for (InventoryItem item : inventory) {
            // Case-insensitive search
            if (item.getName().equalsIgnoreCase(name)) {
                // This calls the specific toString() of Equipment, Furniture, or LabEquipment
                System.out.println(item.toString()); 
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching items found with the name: " + name);
        }
    }

    public void searchEquipment(String category, boolean availableOnly) {
        System.out.println("Searching " + category + " (Available Only: " + availableOnly + ")");
    }

    public void searchEquipment(int minWarranty, int maxWarranty) {
        System.out.println("Searching for warranty between " + minWarranty + " and " + maxWarranty);
    }

    // 5. Validation logic with nested if-else
    public boolean validateAssignment(StaffMember staff, Equipment equipment) {
        if (staff != null) {
            if (equipment != null) {
                if (equipment.isAvailable()) {
                    return true;
                } else {
                    System.out.println("Validation Failed: Equipment unavailable.");
                }
            } else {
                System.out.println("Validation Failed: Equipment record null.");
            }
        } else {
            System.out.println("Validation Failed: Staff record null.");
        }
        return false;
    }
}
