package managers;

import models.*;
import java.util.ArrayList;

public class InventoryReports {

    // 1. For Loop: Display all items and their availability
    public void generateInventoryReport(ArrayList<InventoryItem> items) {
        System.out.println("--- Inventory Status Report ---");
        for (int i = 0; i < items.size(); i++) {
            InventoryItem item = items.get(i);
            String status = item.isAvailable() ? "Available" : "Assigned";
            System.out.println((i + 1) + ". " + item.toString() + " [" + status + "]");
        }
    }

    // 2. While Loop: Find expired warranties (only applicable to Equipment)
    public void findExpiredWarranties(ArrayList<InventoryItem> items) {
        System.out.println("\n--- Expired Warranties ---");
        int index = 0;
        while (index < items.size()) {
            InventoryItem current = items.get(index);
            if (current instanceof Equipment) {
                Equipment eq = (Equipment) current;
                if (eq.getWarrantyMonths() == 0) {
                    System.out.println("EXPIRED: " + eq.getName() + " (Asset ID: " + eq.getId() + ")");
                }
            }
            index++;
        }
    }

    // 3. Enhanced For Loop (ForEach): Group assignments by department/lab
    public void displayAssignmentsByDepartment(ArrayList<LabEquipment> labItems) {
        System.out.println("\n--- Lab Assignments ---");
        for (LabEquipment labEq : labItems) {
            System.out.println("Lab: " + labEq.getLabName() + " | Item: " + labEq.getName());
        }
    }

    // 4. Nested Loops: Calculate utilization statistics (e.g., across multiple labs)
    public void calculateUtilisationRate(String[][] labSchedules) {
        System.out.println("\n--- Utilization Statistics ---");
        for (int row = 0; row < labSchedules.length; row++) {
            int itemsUsed = 0;
            for (int col = 0; col < labSchedules[row].length; col++) {
                if (labSchedules[row][col] != null) itemsUsed++;
            }
            double rate = (double) itemsUsed / labSchedules[row].length * 100;
            System.out.println("Lab " + (row + 1) + " Utilization: " + rate + "%");
        }
    }

    // 5. Do-While Loop: Generate a weekly maintenance schedule
    public void generateMaintenanceSchedule(int totalWeeks) {
        System.out.println("\n--- Weekly Maintenance Schedule ---");
        int week = 1;
        do {
            System.out.println("Week " + week + ": Routine check for all high-priority equipment.");
            week++;
        } while (week <= totalWeeks);
    }
}
