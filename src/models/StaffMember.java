package models;
import exceptions.*;

public class StaffMember {
    private int staffId;
    private String name;
    private String email;
    private Equipment[] assignedEquipment;
    private int count; // Tracks the current number of items in the array

    // Constructor: Initialize details and an empty array of size 5
    public StaffMember(int staffId, String name, String email) {
        this.staffId = staffId;
        this.name = name;
        this.email = email;
        this.assignedEquipment = new Equipment[5];
        this.count = 0;
    }

    // Getters and Setters
    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Equipment[] getAssignedEquipment() { return assignedEquipment; }

    // Adds equipment to the array if there is space (max 5)
    public void addAssignedEquipment(Equipment equipment) throws AssignmentLimitExceededException {
        if (count < 5) {
            assignedEquipment[count] = equipment;
            count++;
        } else {
        	throw new AssignmentLimitExceededException("Limit reached: Cannot assign more than 5 items to " + name);
        }
    }

    // Removes equipment by assetId and shifts remaining items
    public boolean removeAssignedEquipment(String assetId) {
        for (int i = 0; i < count; i++) {
            if (assignedEquipment[i].getId().equals(assetId)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < count - 1; j++) {
                    assignedEquipment[j] = assignedEquipment[j + 1];
                }
                assignedEquipment[count - 1] = null; // Clear the last entry
                count--;
                return true;
            }
        }
        System.out.println("Equipment ID " + assetId + " not found under " + name);
        return false;
    }

    // Returns the number of currently assigned equipment items
    public int getAssignedEquipmentCount() {
        return count;
    }

    @Override
    public String toString() {
        return "StaffMember ID:" + staffId + ", Name:" + name + ", Assigned Items:" + count + "/5]";
    }
}